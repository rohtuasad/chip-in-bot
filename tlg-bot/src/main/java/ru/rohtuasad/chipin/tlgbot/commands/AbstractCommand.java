package ru.rohtuasad.chipin.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractCommand extends BotCommand {

  public AbstractCommand(String commandIdentifier, String description) {
    super(commandIdentifier, description);
  }

  protected void sendMessage(AbsSender absSender, Chat chat, String answerText) {
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
