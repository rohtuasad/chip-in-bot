package ru.rohtuasad.chipin.core.transfer.service;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements
    TransferService {

  @Override
  public void send(UUID partyId, UUID senderId, @NonNull String receiverNickName, BigDecimal amount) {

  }
}
