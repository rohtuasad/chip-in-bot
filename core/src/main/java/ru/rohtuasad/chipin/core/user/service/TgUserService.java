package ru.rohtuasad.chipin.core.user.service;

import java.util.List;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TgUserService {
  TgUser getUser(long id);
  List<TgUser> getUsers(List<Long> ids);
  TgUser saveUser(TgUser tgUser);
}
