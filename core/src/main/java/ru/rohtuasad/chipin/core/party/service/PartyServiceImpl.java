package ru.rohtuasad.chipin.core.party.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.model.PartyUser;
import ru.rohtuasad.chipin.core.party.repository.PartyRepository;
import ru.rohtuasad.chipin.core.user.model.User;
import ru.rohtuasad.chipin.core.user.service.UserService;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {

  private final PartyRepository partyRepository;
  private final UserService userService;

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
  @Nullable
  public Party getActiveParty(@NonNull Long chatId) {
    return partyRepository.findPartyByIsActiveAndChatId(true, chatId);
  }

  public Party addUser(UUID partyId, User user) {
    user = userService.saveUser(user);
    final Party party = getParty(partyId);
    party.addUser(user);
    return partyRepository.save(party);
  }

  public boolean isUserInParty(UUID partyId, long userId) {
    return getParty(partyId).getUsers().contains(new PartyUser(userId));
  }

  private Party getParty(UUID partyId) {
    final Optional<Party> optionalParty = partyRepository.findById(partyId);
    if (optionalParty.isPresent()) {
      return optionalParty.get();
    }
    throw new IllegalStateException("Не найдена пати с guid " + partyId);
  }

  @Override
  public void endActiveParty(@NonNull Long chatId) {

  }
}
