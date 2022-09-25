package ru.rohtuasad.chipin.core.payment.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Payment {

  @Id
  private UUID paymentId;
  private UUID partyId;
  private Long userId;
  private BigDecimal amount;
  private String description;
}
