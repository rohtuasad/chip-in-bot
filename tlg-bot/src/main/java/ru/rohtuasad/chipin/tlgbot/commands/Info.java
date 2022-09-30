package ru.rohtuasad.chipin.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
public class Info extends AbstractCommand {

  public Info() {
    super("info", "Получить информацию о пати");
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

  }
}
