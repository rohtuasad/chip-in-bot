package ru.rohtuasad.chipin.core.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rohtuasad.chipin.core.user.model.User;

@SpringBootTest()
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @BeforeEach
  void init() {
    User user = new User();
    user.setUserTgId(1L);
    user.setUserName("@Username");
    user.setNickName("Nickname");
    userService.saveUser(user);
  }

  @Test
  void getUser() {
    final User user = userService.getUser(1);
    assertEquals("@Username", user.getUserName());
  }

  @Test
  void saveUser() {
    User user = new User();
    user.setUserTgId(2L);
    user.setUserName("@Username2");
    user.setNickName("Nickname two");
    userService.saveUser(user);

    final User savedUser = userService.getUser(2);
    assertEquals("@Username2", user.getUserName());
    assertEquals("Nickname two", user.getNickName());
  }

  @Test
  void updateUser() {
    User user = new User();
    user.setUserTgId(1L);
    user.setUserName("@Username1");
    user.setNickName("Nickname one");
    userService.saveUser(user);

    final User savedUser = userService.getUser(1);
    assertEquals("@Username1", user.getUserName());
    assertEquals("Nickname one", user.getNickName());
  }
}