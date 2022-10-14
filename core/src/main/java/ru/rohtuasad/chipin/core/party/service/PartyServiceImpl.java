package ru.rohtuasad.chipin.core.party.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.dto.PartyInfo;
import ru.rohtuasad.chipin.core.party.dto.PartyUserInfo;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.model.PartyUser;
import ru.rohtuasad.chipin.core.party.repository.PartyRepository;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.payment.service.PaymentService;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.transfer.service.TransferService;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {

  private final PartyRepository partyRepository;
  private final TgUserService tgUserService;
  private final PaymentService paymentService;
  private final TransferService transferService;

  @Override
  public Party createParty(@NonNull Long chatId) {
    final Party activeParty = partyRepository.findPartyByIsActiveAndChatId(true,
        chatId);
    if (activeParty != null) {
      throw new IllegalStateException("Уже есть активная пати");
    }
    Party party = new Party();
    party.setChatId(chatId);
    party.setIsActive(true);
    return partyRepository.save(party);
  }

  @Override
  @Nullable
  public Party getActiveParty(@NonNull Long chatId) {
    Party partyByIsActiveAndChatId = partyRepository.findPartyByIsActiveAndChatId(true, chatId);
    if (partyByIsActiveAndChatId == null) {
      throw new IllegalStateException("Нет активной пати");
    }
    return partyByIsActiveAndChatId;
  }

  public Party addUser(UUID partyId, TgUser tgUser) {
    tgUser = tgUserService.saveUser(tgUser);
    final Party party = getParty(partyId);
    party.addUser(tgUser);
    return partyRepository.save(party);
  }

  public boolean isUserInParty(UUID partyId, long userId) {
    return getParty(partyId).getUsers().contains(new PartyUser(userId));
  }

  @Override
  public PartyInfo getPartyInfo(UUID partyId) {
    Optional<Party> optionalParty = partyRepository.findById(partyId);

    if (optionalParty.isEmpty()) {
      throw new IllegalStateException("Не запущена пати");
    }
    PartyInfo partyInfo = new PartyInfo();
    Party party = optionalParty.get();
    List<TgUser> users = tgUserService.getUsers(party);
    users.forEach(
        user -> partyInfo.getPartyUserInfoMap().put(user.getId(), new PartyUserInfo(user)));

    final BigDecimal[] totalPayments = {BigDecimal.valueOf(0)};
    List<Payment> payments = paymentService.getPartyPayments(partyId);
    payments.forEach(payment -> {
      PartyUserInfo partyUserInfo = partyInfo.getPartyUserInfoMap().get(payment.getUserId());
      if (partyUserInfo != null) {
        partyUserInfo.getPaymentSet().add(payment);
        partyUserInfo.setTotal(partyUserInfo.getTotal().add(payment.getAmount()));
      }
      totalPayments[0] = totalPayments[0].add(payment.getAmount());
    });

    List<Transfer> transfers = transferService.getPartyTransfers(partyId);
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

  private Party getParty(UUID partyId) {
    final Optional<Party> optionalParty = partyRepository.findById(partyId);
    if (optionalParty.isPresent()) {
      return optionalParty.get();
    }
    throw new IllegalStateException("Не найдена пати с guid " + partyId);
  }

  @Override
  public void endActiveParty(@NonNull Long chatId) {

  }
}
