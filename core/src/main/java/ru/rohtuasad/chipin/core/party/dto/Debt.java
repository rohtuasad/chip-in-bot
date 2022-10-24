package ru.rohtuasad.chipin.core.party.dto;

import java.math.BigDecimal;
import lombok.Data;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@Data
public class Debt {
  private TgUser creditor;
  private BigDecimal amount = BigDecimal.ZERO;
}
