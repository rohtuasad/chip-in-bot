package ru.rohtuasad.chipin.core.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.chat.service.ChatService;

@Component
@RequiredArgsConstructor
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final ChatService chatService;

  @SneakyThrows
  @Override
  public void onApplicationEvent(@NonNull ContextRefreshedEvent refreshedEvent) {
    Chat chat = new Chat();
    chat.setTgChatId(1L);
    chat.setTgChatName("Chat name");
    chatService.saveChat(chat);

    Chat chat2 = new Chat();
    chat2.setTgChatId(2L);
    chat2.setTgChatName("Chat two");
    chatService.saveChat(chat2);
  }
}
