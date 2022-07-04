package ru.rohtuasad.chipin.core.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.chat.model.Chat;

public interface ChatRepository extends CrudRepository<Chat, String> {
}
