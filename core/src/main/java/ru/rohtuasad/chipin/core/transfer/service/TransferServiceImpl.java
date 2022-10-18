package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyService;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.transfer.repository.TransferRepository;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private final TgUserService tgUserService;
  private final PartyService partyService;
  private final TransferRepository transferRepository;

  @Override
  public void send(Long chatId, Long senderId, @NonNull String receiverUserName,
      BigDecimal amount) {
    TgUser receiver = tgUserService.getUser(receiverUserName);
    Party activeParty = partyService.getActiveParty(chatId);
    if (!partyService.isUserInParty(activeParty.getPartyId(), senderId)) {
      throw new IllegalStateException("Отправитель не в пати");
    }
    if (!partyService.isUserInParty(activeParty.getPartyId(), receiver.getUserTgId())) {
      throw new IllegalStateException("Получатель не в пати");
    }
    Transfer transfer = new Transfer();
    transfer.setPartyId(activeParty.getPartyId());
    transfer.setSenderId(senderId);
    transfer.setReceiverId(receiver.getId());
    transfer.setAmount(amount);
    transferRepository.save(transfer);
  }

  @Override
  public List<Transfer> getPartyTransfers(UUID partyId) {
    return transferRepository.findAllByPartyId(partyId);
  }
}
