package ru.rohtuasad.chipin.tlgbot;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.chat.service.ChatService;
import ru.rohtuasad.chipin.tlgbot.commands.IAmIn;
import ru.rohtuasad.chipin.tlgbot.commands.PartyStart;
import ru.rohtuasad.chipin.tlgbot.commands.Pay;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandHandler extends TelegramLongPollingCommandBot {

  @Value("${bot.token}")
  private String botToken;

  @Value("${bot.username}")
  private String botUsername;

  private final ChatService chatService;
  private final PartyStart partyStart;
  private final Pay pay;
  private final IAmIn iAmIn;

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @PostConstruct
  public void init() {
    register(partyStart);
    register(pay);
    register(iAmIn);
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
