package ru.rohtuasad.chipin.core.user.service;

import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TgUserService {
  TgUser getUser(long id);
  TgUser saveUser(TgUser tgUser);
}
