package ru.rohtuasad.chipin.core.chat.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.chat.repository.ChatRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;

  @Override
  @Nullable
  public Chat getChat(UUID uuid) {
    final Optional<Chat> chat = chatRepository.findById(uuid);
    if (chat.isPresent()) {
      return chat.get();
    }
    log.warn("Не удалось найти чат с uuid = {}", uuid);
    return null;
  }

  @Nullable
  public Chat findByTgChatId(String tlgChatId) {
    return chatRepository.findByTgChatId(tlgChatId);
  }

  public Chat saveChat(Chat chat) {
    if (chat.getId() == null && chat.getTgChatId() != null) {
      // Нужно проверить по tgChatId, возможно такой уже есть в базе
      final Chat byTgChatId = findByTgChatId(chat.getTgChatId());
      if (byTgChatId != null) {
        return byTgChatId;
      }
    }
    return chatRepository.save(chat);
  }
}
