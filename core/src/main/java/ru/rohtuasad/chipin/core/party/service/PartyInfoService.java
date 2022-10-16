package ru.rohtuasad.chipin.core.party.service;

import ru.rohtuasad.chipin.core.party.dto.PartyInfo;

public interface PartyInfoService {
  PartyInfo getPartyInfo(Long chatId);
}
