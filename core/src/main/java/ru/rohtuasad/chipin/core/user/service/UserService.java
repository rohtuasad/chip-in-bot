package ru.rohtuasad.chipin.core.user.service;

import ru.rohtuasad.chipin.core.user.model.User;

public interface UserService {
  User getUser(long id);
  User saveUser(User user);
}
