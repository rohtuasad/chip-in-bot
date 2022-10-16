package ru.rohtuasad.chipin.core.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyService;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@SpringBootTest()
@ActiveProfiles("test")
class TgUserServiceImplTest {

  @Autowired
  private TgUserService tgUserService;
  @Autowired
  private PartyService partyService;

  @BeforeEach
  void init() {
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(1L);
    tgUser.setUserName("@Username");
    tgUser.setNickName("Nickname");
    tgUserService.saveUser(tgUser);
  }

  @Test
  void getUser() {
    final TgUser tgUser = tgUserService.getUser(1);
    assertEquals("@Username", tgUser.getUserName());
  }

  @Test
  void saveUser() {
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(2L);
    tgUser.setUserName("@Username2");
    tgUser.setNickName("Nickname two");
    tgUserService.saveUser(tgUser);

    final TgUser savedTgUser = tgUserService.getUser(2);
    assertEquals("@Username2", savedTgUser.getUserName());
    assertEquals("Nickname two", savedTgUser.getNickName());
  }

  @Test
  void updateUser() {
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(1L);
    tgUser.setUserName("@Username1");
    tgUser.setNickName("Nickname one");
    tgUserService.saveUser(tgUser);

    final TgUser savedTgUser = tgUserService.getUser(1);
    assertEquals("@Username1", savedTgUser.getUserName());
    assertEquals("Nickname one", savedTgUser.getNickName());
  }

  @Test
  void getAllByIds() {
    Party activeParty = partyService.getActiveParty(4L);

    TgUser tgUser3 = new TgUser();
    tgUser3.setUserTgId(3L);
    tgUser3.setUserName("@Username3");
    tgUser3.setNickName("Nickname three");
    tgUserService.saveUser(tgUser3);

    TgUser tgUser4 = new TgUser();
    tgUser4.setUserTgId(4L);
    tgUser4.setUserName("@Username4");
    tgUser4.setNickName("Nickname four");
    tgUserService.saveUser(tgUser4);

    activeParty.addUser(tgUser3);
    activeParty.addUser(tgUser4);

    List<TgUser> users = tgUserService.getUsers(activeParty);
    assertEquals(2, users.size());
    assertEquals("@Username3", users.get(0).getUserName());
    assertEquals("@Username4", users.get(1).getUserName());
  }
}