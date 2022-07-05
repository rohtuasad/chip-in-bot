package ru.rohtuasad.chipin.core.party.model;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Party {
  @Id
  private UUID partyId;
  private String partyName;
  private Boolean isActive;
  private Long partyChatFk;
}
