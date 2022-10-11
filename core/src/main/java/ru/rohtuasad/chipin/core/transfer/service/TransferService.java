package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;

public interface TransferService {

  void send(Long chatId, Long senderId, @NonNull String receiverNickName, BigDecimal amount);

  List<Transfer> getPartyTransfers(UUID partyId);
}
