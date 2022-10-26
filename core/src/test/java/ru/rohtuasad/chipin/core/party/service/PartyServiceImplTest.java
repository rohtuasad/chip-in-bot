package ru.rohtuasad.chipin.core.party.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.user.model.TgUser;

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
    Exception exception = assertThrows(IllegalStateException.class,
        () -> partyService.getActiveParty(3L));
    assertEquals("Нет активной пати", exception.getMessage());
  }

  @Test
  void addUser() {
    final Party activeParty = partyService.getActiveParty(1L);
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(1L);
    tgUser.setUserName("User name");
    tgUser.setNickName("User nick name");

    assertNotNull(activeParty);
    assertEquals(0, activeParty.getUsers().size());
    partyService.addUser(activeParty.getPartyId(), tgUser);
    final Party activeParty1 = partyService.getActiveParty(1L);
    assertNotNull(activeParty1);
    assertEquals(1, activeParty1.getUsers().size());

    partyService.addUser(activeParty.getPartyId(), tgUser);
    final Party activeParty2 = partyService.getActiveParty(1L);
    assertNotNull(activeParty2);
    assertEquals(1, activeParty2.getUsers().size());
  }

  @Test
  void isUserInParty() {
    final Party activeParty = partyService.getActiveParty(1L);
    assert activeParty != null;
    assertFalse(partyService.isUserInParty(activeParty.getPartyId(), 2L));

    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(2L);
    tgUser.setUserName("User name");
    tgUser.setNickName("User nick name");

    partyService.addUser(activeParty.getPartyId(), tgUser);
    assertTrue(partyService.isUserInParty(activeParty.getPartyId(), 2L));
  }

  @Test
  void endActiveParty() {
  }
}