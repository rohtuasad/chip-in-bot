package ru.rohtuasad.chipin.core.party.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@Data
public class PartyUserInfo {
  private final TgUser user;
  private BigDecimal total;
  private Set<Payment> paymentSet = new HashSet<>();
  private Set<Transfer> transferSet = new HashSet<>();
  private Set<Debt> debt = new HashSet<>();

  public PartyUserInfo(TgUser user) {
    this.user = user;
  }
}
