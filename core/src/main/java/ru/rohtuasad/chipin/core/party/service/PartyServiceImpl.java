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
  public void createParty(@NonNull Long chatId) {

  }

  @Override
  public Party getActiveParty(@NonNull Long chatId) {
    return null;
  }

  @Override
  public void endActiveParty(@NonNull Long chatId) {

  }
}
