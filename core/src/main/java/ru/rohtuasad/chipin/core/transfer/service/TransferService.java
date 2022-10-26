package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TransferService {

  void send(Long chatId, TgUser sender, @NonNull String receiverUserName, BigDecimal amount);

  List<Transfer> getPartyTransfers(UUID partyId);
}
