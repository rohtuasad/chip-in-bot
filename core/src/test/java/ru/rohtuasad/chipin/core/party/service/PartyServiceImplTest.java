package ru.rohtuasad.chipin.core.party.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.user.model.User;

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
  void getActivePartyWhenNotExists() {
    final Party activeParty = partyService.getActiveParty(3L);
    assertNull(activeParty);
  }

  @Test
  void addUser() {
    final Party activeParty = partyService.getActiveParty(1L);
    User user = new User();
    user.setUserTgId(1L);
    user.setUserName("User name");
    user.setNickName("User nick name");

    assert activeParty != null;
    assertEquals(0, activeParty.getUsers().size());
    partyService.addUser(activeParty.getPartyId(), user);
    final Party activeParty1 = partyService.getActiveParty(1L);
    assert activeParty1 != null;
    assertEquals(1, activeParty1.getUsers().size());
  }

  @Test
  void isUserInParty() {
    final Party activeParty = partyService.getActiveParty(1L);
    assert activeParty != null;
    assertFalse(partyService.isUserInParty(activeParty.getPartyId(), 2L));

    User user = new User();
    user.setUserTgId(2L);
    user.setUserName("User name");
    user.setNickName("User nick name");

    partyService.addUser(activeParty.getPartyId(), user);
    assertTrue(partyService.isUserInParty(activeParty.getPartyId(), 2L));
  }

  @Test
  void endActiveParty() {
  }
}