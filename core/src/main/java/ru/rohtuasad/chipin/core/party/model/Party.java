package ru.rohtuasad.chipin.core.party.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@Data
public class Party {
  @Id
  private UUID partyId;
  private String partyName;
  private Boolean isActive;
  private Long chatId;

  @MappedCollection(idColumn = "PARTY_ID")
  private Set<PartyUser> users = new HashSet<>();

  public void addUser(TgUser tgUser) {
    users.add(new PartyUser(tgUser.getId()));
  }
}
