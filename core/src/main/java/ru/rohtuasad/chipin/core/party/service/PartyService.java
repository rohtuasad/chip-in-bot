package ru.rohtuasad.chipin.core.party.service;

import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.party.model.Party;

public interface PartyService {
  Party createParty(@NonNull Long chatId);
  Party getActiveParty(@NonNull Long chatId);
  void endActiveParty(@NonNull Long chatId);
}
