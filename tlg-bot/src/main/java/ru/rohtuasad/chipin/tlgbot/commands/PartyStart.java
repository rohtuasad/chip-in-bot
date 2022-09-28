package ru.rohtuasad.chipin.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rohtuasad.chipin.core.chat.service.ChatService;
import ru.rohtuasad.chipin.core.party.service.PartyService;

@Slf4j
@Component
public class PartyStart extends AbstractCommand {

  private final PartyService partyService;
  private final ChatService chatService;

  public PartyStart(PartyService partyService, ChatService chatService) {
    super("party_start", "Начать подготовку к вечеринке");
    this.partyService = partyService;
    this.chatService = chatService;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    if ("group".equals(chat.getType())) {
      try {
        ru.rohtuasad.chipin.core.chat.model.Chat savedChat =
            new ru.rohtuasad.chipin.core.chat.model.Chat();
        savedChat.setTgChatId(chat.getId());
        savedChat.setTgChatName(chat.getTitle());
        chatService.saveChat(savedChat);

        partyService.createParty(chat.getId());
        sendMessage(absSender, chat, "Начинаем готовиться к пати");
      } catch (IllegalStateException e) {
        if ("Уже есть активная пати".equals(e.getMessage())) {
          sendMessage(absSender, chat, "Сначала нужно закончить текущую пати");
        }
      }
    } else if ("private".equals(chat.getType())) {
      sendMessage(absSender, chat, "Какая пати, ты тут один");
    }
  }
}
