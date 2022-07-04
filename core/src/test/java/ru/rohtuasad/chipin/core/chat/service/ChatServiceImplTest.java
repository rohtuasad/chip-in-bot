package ru.rohtuasad.chipin.core.chat.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rohtuasad.chipin.core.chat.model.Chat;

@SpringBootTest()
class ChatServiceImplTest {
  @Autowired
  private ChatService chatService;

  @Test
  void getChat() {
    final Chat chat = chatService.getChat("1");
    assertEquals("Chat name", chat.getTgChatName());
  }

  @Test
  void saveChat() {
    Chat chat = new Chat();
    chat.setTgChatId("3");
    chat.setTgChatName("Chat name three");
    chatService.saveChat(chat);

    final Chat savedChat = chatService.getChat("3");
    assertEquals("Chat name three", savedChat.getTgChatName());
  }

  @Test
  void updateChat() {
    Chat chat = new Chat();
    chat.setTgChatId("2");
    chat.setTgChatName("Chat name two");
    chatService.saveChat(chat);

    final Chat savedChat = chatService.getChat("2");
    assertEquals("Chat name two", savedChat.getTgChatName());
  }
}