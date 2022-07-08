package ru.rohtuasad.chipin.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rohtuasad.chipin.core.party.service.PartyService;

@Slf4j
@Component
public class PartyStart extends BotCommand {

  private final PartyService partyService;

  public PartyStart(PartyService partyService) {
    super("party_start", "Начать подготовку к вечеринке");
    this.partyService = partyService;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    if ("group".equals(chat.getType())) {
      try {
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

  private void sendMessage(AbsSender absSender, Chat chat, String answerText) {
    SendMessage answer = new SendMessage();
    answer.setChatId(chat.getId().toString());
    answer.setText(answerText);

    try {
      absSender.execute(answer);
    } catch (TelegramApiException e) {
      log.error("Error while sending message", e);
    }
  }
}
