package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import org.springframework.lang.NonNull;

public interface TransferService {

  void send(Long chatId, Long senderId, @NonNull String receiverNickName, BigDecimal amount);
}
