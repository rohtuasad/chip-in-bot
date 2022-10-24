package ru.rohtuasad.chipin.core.party.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class PartyInfo {
  private Map<Long, PartyUserInfo> partyUserInfoMap = new HashMap<>();
  private BigDecimal total;
  private BigDecimal middleValue;
}
