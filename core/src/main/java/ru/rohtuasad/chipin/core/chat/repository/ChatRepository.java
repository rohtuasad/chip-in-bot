package ru.rohtuasad.chipin.core.chat.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.chat.model.Chat;

public interface ChatRepository extends CrudRepository<Chat, UUID> {
  Chat findByTgChatId(String tgChatId);
}
