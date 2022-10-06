package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.lang.NonNull;

public interface TransferService {

  void send(UUID partyId, UUID senderId, @NonNull String receiverNickName, BigDecimal amount);
}
