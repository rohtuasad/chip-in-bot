package ru.rohtuasad.chipin.tlgbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.chat.service.ChatService;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandHandler extends TelegramLongPollingCommandBot {

  @Value("${bot.token}")
  private String botToken;

  @Value("${bot.username}")
  private String botUsername;

  private final ChatService chatService;

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public void processNonCommandUpdate(Update update) {
    if (update.hasMessage() && !update.getMessage().getNewChatMembers().isEmpty()) {
      for (User newChatMember : update.getMessage().getNewChatMembers()) {
        if ("PartyChipInBot".equals(newChatMember.getUserName())) {
          if ("group".equals(update.getMessage().getChat().getType())) {
            final Long id = update.getMessage().getChat().getId();
            final String title = update.getMessage().getChat().getTitle();
            Chat chat = new Chat();
            chat.setTgChatId(id);
            chat.setTgChatName(title);
            chatService.saveChat(chat);
            return;
          }
        }
      }
    }
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
