package ru.rohtuasad.chipin.core.party.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.party.model.Party;

public interface PartyRepository extends CrudRepository<Party, UUID> {
  Party findPartyByIsActiveAndChatId(Boolean isActive, Long chatId);
}
