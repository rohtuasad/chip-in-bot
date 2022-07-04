package ru.rohtuasad.chipin.core.chat.service;

import ru.rohtuasad.chipin.core.chat.model.Chat;

public interface ChatService {
  Chat getChat(String uuid);
  Chat saveChat(Chat chat);
}
