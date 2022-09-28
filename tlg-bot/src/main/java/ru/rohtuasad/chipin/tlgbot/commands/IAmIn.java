package ru.rohtuasad.chipin.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyService;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@Slf4j
@Component
public class IAmIn extends AbstractCommand {
  private final PartyService partyService;

  public IAmIn(PartyService partyService) {
    super("i_am_in", "Принять участие в вечеринке");
    this.partyService = partyService;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    if ("group".equals(chat.getType())) {
      try {
        Party activeParty = partyService.getActiveParty(chat.getId());
         if (!partyService.isUserInParty(activeParty.getPartyId(), user.getId())) {
           TgUser partyTgUser = new TgUser();
           partyTgUser.setUserTgId(user.getId());
           partyTgUser.setUserName(user.getUserName());
           partyTgUser.setNickName(user.getFirstName() + user.getLastName());

           partyService.addUser(activeParty.getPartyId(), partyTgUser);
         }
        sendMessage(absSender, chat, "Тебя посчитали");
      } catch (IllegalStateException e) {
        if ("Нет активной пати".equals(e.getMessage())) {
          sendMessage(absSender, chat, "Сначала нужно запустить подготовку к пати");
        }
      }
    } else if ("private".equals(chat.getType())) {
      sendMessage(absSender, chat, "Какая оплата, ты тут один");
    }
  }
}
