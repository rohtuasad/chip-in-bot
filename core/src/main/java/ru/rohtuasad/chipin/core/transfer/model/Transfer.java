package ru.rohtuasad.chipin.core.transfer.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Transfer {
  @Id
  private UUID transferId;
  private UUID partyId;
  private Long senderId;
  private Long receiverId;
  private BigDecimal amount;
}
