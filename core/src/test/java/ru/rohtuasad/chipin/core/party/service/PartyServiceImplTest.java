package ru.rohtuasad.chipin.core.party.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;

@SpringBootTest()
@ActiveProfiles("test")
class PartyServiceImplTest {

  @Autowired
  PartyServiceImpl partyService;

  @Test
  void createNewParty() {
    final Party party = partyService.createParty(2L);
    assertNotNull(party.getPartyId());
  }

  @Test
  void createPartyWhileExisting() {
    Exception exception = assertThrows(IllegalStateException.class,
        () -> partyService.createParty(1L));
    assertEquals("Уже есть активная пати", exception.getMessage());
  }

  @Test
  void getActiveParty() {
    final Party activeParty = partyService.getActiveParty(1L);
    assertNotNull(activeParty);
  }

  @Test
  void endActiveParty() {
  }
}