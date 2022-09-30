package ru.rohtuasad.chipin.core.party.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class PartyInfo {
  Map<Long, PartyUserInfo> partyUserInfoMap = new HashMap<>();
}
