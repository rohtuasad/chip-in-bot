package ru.rohtuasad.chipin.core.party.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.dto.PartyInfo;
import ru.rohtuasad.chipin.core.party.dto.PartyUserInfo;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.payment.service.PaymentService;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.transfer.service.TransferService;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@Service
@RequiredArgsConstructor
public class PartyInfoServiceImpl implements PartyInfoService {

  private final PaymentService paymentService;
  private final TransferService transferService;
  private final PartyService partyService;
  private final TgUserService tgUserService;

  @Override
  public PartyInfo getPartyInfo(Long chatId) {
    Party party = partyService.getActiveParty(chatId);

    PartyInfo partyInfo = new PartyInfo();
    List<TgUser> users = tgUserService.getUsers(party);
    users.forEach(
        user -> partyInfo.getPartyUserInfoMap().put(user.getId(), new PartyUserInfo(user)));

    final BigDecimal[] totalPayments = {BigDecimal.valueOf(0)};
    List<Payment> payments = paymentService.getPartyPayments(party.getPartyId());
    payments.forEach(payment -> {
      PartyUserInfo partyUserInfo = partyInfo.getPartyUserInfoMap().get(payment.getUserId());
      if (partyUserInfo != null) {
        partyUserInfo.getPaymentSet().add(payment);
        partyUserInfo.setTotal(partyUserInfo.getTotal().add(payment.getAmount()));
      }
      totalPayments[0] = totalPayments[0].add(payment.getAmount());
    });

    List<Transfer> transfers = transferService.getPartyTransfers(party.getPartyId());
    transfers.forEach(transfer -> {
      PartyUserInfo senderUserInfo = partyInfo.getPartyUserInfoMap().get(transfer.getSenderId());
      if (senderUserInfo != null) {
        senderUserInfo.getTransferSet().add(transfer);
        senderUserInfo.setTotal(senderUserInfo.getTotal().add(transfer.getAmount()));
      }

      PartyUserInfo receiverUserInfo = partyInfo.getPartyUserInfoMap()
          .get(transfer.getReceiverId());
      if (receiverUserInfo != null) {
        receiverUserInfo.setTotal(receiverUserInfo.getTotal().subtract(transfer.getAmount()));
      }
    });

    BigDecimal total = totalPayments[0];
    BigDecimal middleValue = total.divide(
        BigDecimal.valueOf(partyInfo.getPartyUserInfoMap().size()), MathContext.DECIMAL32);

    partyInfo.getPartyUserInfoMap().values().forEach(user ->
        user.setTotal(user.getTotal().subtract(middleValue))
    );

    return partyInfo;
  }
}
