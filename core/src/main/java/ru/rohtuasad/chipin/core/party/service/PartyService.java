package ru.rohtuasad.chipin.core.party.service;

import java.util.UUID;
import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface PartyService {
  Party createParty(@NonNull Long chatId);
  Party getActiveParty(@NonNull Long chatId);
  void endActiveParty(@NonNull Long chatId);
  Party addUser(UUID partyId, TgUser tgUser);
  boolean isUserInParty(UUID partyId, long userId);
}
