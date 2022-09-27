package ru.rohtuasad.chipin.core.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@SpringBootTest()
class TgUserServiceImplTest {

  @Autowired
  private TgUserService tgUserService;

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
}