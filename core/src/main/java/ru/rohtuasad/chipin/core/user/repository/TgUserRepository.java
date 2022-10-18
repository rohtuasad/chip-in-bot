package ru.rohtuasad.chipin.core.user.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TgUserRepository extends CrudRepository<TgUser, Long> {
  List<TgUser> findAllByUserTgIdIn(List<Long> ids);
  Optional<TgUser> findByUserName(String userName);
}
