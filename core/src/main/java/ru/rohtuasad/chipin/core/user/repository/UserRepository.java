package ru.rohtuasad.chipin.core.user.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.user.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
