package ru.rohtuasad.chipin.tlgbot.commands;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyService;
import ru.rohtuasad.chipin.core.payment.service.PaymentService;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@Slf4j
@Component
public class Pay extends AbstractCommand {

  private final PaymentService paymentService;
  private final PartyService partyService;

  public Pay(PaymentService paymentService, PartyService partyService) {
    super("pay", "Произвести оплату вечеринки");
    this.paymentService = paymentService;
    this.partyService = partyService;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    if ("group".equals(chat.getType())) {
      try {
        Party activeParty = partyService.getActiveParty(chat.getId());
        TgUser partyTgUser = new TgUser();
        partyTgUser.setUserTgId(user.getId());
        partyTgUser.setUserName(user.getUserName());
        partyTgUser.setNickName(user.getFirstName() + user.getLastName());

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
          description.append(strings[i]).append(" ");
        }

        paymentService.addPayment(
            activeParty.getPartyId(), partyTgUser, BigDecimal.valueOf(Long.parseLong(strings[0])),
            description.toString());

        sendMessage(absSender, chat, "Платёж учтён");
      } catch (IllegalStateException e) {
        if ("Нет активной пати".equals(e.getMessage())) {
          sendMessage(absSender, chat, "Сначала нужно запустить подготовку к пати");
        }
      } catch (NumberFormatException e) {
        sendMessage(absSender, chat, "Первым аргументом должна идти выплаченная сумма");
      } catch (ArrayIndexOutOfBoundsException e) {
        sendMessage(absSender, chat,
            "Первым аргументом должна идти выплаченная сумма, а вторым описание платежа");
      }
    }
  }


}
