package ru.rohtuasad.chipin.core.chat.service;

import java.util.Optional;
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
  public Chat getChat(long id) {
    final Optional<Chat> chat = chatRepository.findById(id);
    if (chat.isPresent()) {
      return chat.get();
    }
    log.warn("Не удалось найти чат с uuid = {}", id);
    return null;
  }

  public Chat saveChat(Chat chat) {
    if (chat.getId() != null) {
      // Нужно проверить по tgChatId, возможно такой уже есть в базе
      final Chat byTgChatId = getChat(chat.getId());
      if (byTgChatId != null) {
        chat.setIsNew(false);
        return chatRepository.save(chat);
      }
    }
    chat.setIsNew(true);
    return chatRepository.save(chat);
  }
}
