package ru.rohtuasad.chipin.core.user.service;

import java.util.List;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TgUserService {
  TgUser getUser(long id);
  TgUser getUser(String userName);
  List<TgUser> getUsers(Party party);
  TgUser saveUser(TgUser tgUser);
}
