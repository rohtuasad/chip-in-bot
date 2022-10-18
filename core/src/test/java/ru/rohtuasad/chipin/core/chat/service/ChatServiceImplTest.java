package ru.rohtuasad.chipin.core.chat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.chat.model.Chat;

@SpringBootTest()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class ChatServiceImplTest {
  @Autowired
  private ChatService chatService;

  @Test
  @Order(1)
  void getChat() {
    final Chat chat = chatService.getChat(1L);
    assertEquals("Chat name", chat.getTgChatName());
  }

  @Test
  @Order(2)
  void saveChat() {
    Chat chat = new Chat();
    chat.setTgChatId(2000L);
    chat.setTgChatName("Chat name two thousands");
    chatService.saveChat(chat);

    final Chat savedChat = chatService.getChat(2000L);
    assertEquals("Chat name two thousands", savedChat.getTgChatName());
  }

  @Test
  @Order(3)
  void updateChat() {
    Chat chat = new Chat();
    chat.setTgChatId(1L);
    chat.setTgChatName("Chat name one");
    chatService.saveChat(chat);

    final Chat savedChat = chatService.getChat(1);
    assertEquals("Chat name one", savedChat.getTgChatName());
  }
}