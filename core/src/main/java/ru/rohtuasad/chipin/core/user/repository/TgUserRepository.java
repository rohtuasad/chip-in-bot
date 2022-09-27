package ru.rohtuasad.chipin.core.user.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.user.model.TgUser;

public interface TgUserRepository extends CrudRepository<TgUser, Long> {
}
