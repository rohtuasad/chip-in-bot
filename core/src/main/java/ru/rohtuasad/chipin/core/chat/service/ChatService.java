package ru.rohtuasad.chipin.core.chat.service;

import java.util.UUID;
import ru.rohtuasad.chipin.core.chat.model.Chat;

public interface ChatService {
  Chat getChat(UUID uuid);
  Chat findByTgChatId(String tlgChatId);
  Chat saveChat(Chat chat);
}
