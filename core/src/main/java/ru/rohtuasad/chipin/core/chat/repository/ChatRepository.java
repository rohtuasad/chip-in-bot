package ru.rohtuasad.chipin.core.chat.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rohtuasad.chipin.core.chat.model.Chat;

@Repository
public interface ChatRepository extends CrudRepository<Chat, UUID> {
  Chat findByTgChatId(String tgChatId);
}
