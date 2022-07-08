package ru.rohtuasad.chipin.core.party.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.repository.PartyRepository;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService  {
  private final PartyRepository partyRepository;

  @Override
  public Party createParty(@NonNull Long chatId) {
    final Party activeParty = partyRepository.findPartyByIsActiveAndChatId(true,
        chatId);
    if (activeParty != null) {
      throw new IllegalStateException("Уже есть активная пати");
    }
    Party party = new Party();
    party.setChatId(chatId);
    party.setIsActive(true);
    return partyRepository.save(party);
  }

  @Override
  public Party getActiveParty(@NonNull Long chatId) {
    return partyRepository.findPartyByIsActiveAndChatId(true, chatId);
  }

  @Override
  public void endActiveParty(@NonNull Long chatId) {

  }
}
