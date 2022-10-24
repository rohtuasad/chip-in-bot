package ru.rohtuasad.chipin.core.party.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.chat.service.ChatService;
import ru.rohtuasad.chipin.core.party.dto.PartyInfo;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.payment.service.PaymentService;
import ru.rohtuasad.chipin.core.transfer.service.TransferService;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@SpringBootTest()
@ActiveProfiles("test")
class PartyInfoServiceImplTest {

  @Autowired
  ChatService chatService;
  @Autowired
  PartyService partyService;
  @Autowired
  TgUserService tgUserService;
  @Autowired
  PartyInfoService partyInfoService;
  @Autowired
  PaymentService paymentService;

  @Autowired
  TransferService transferService;

  @Test
  void getPartyInfo() {
    Chat chat = new Chat();
    chat.setTgChatId(5L);
    chatService.saveChat(chat);

    Party party = partyService.createParty(5L);

    TgUser tgUser1 = makeUser(1L, "@Username1");
    TgUser tgUser2 = makeUser(2L, "@Username2");
    TgUser tgUser3 = makeUser(3L, "@Username3");
    TgUser tgUser4 = makeUser(4L, "@Username4");
    TgUser tgUser5 = makeUser(5L, "@Username5");

    partyService.addUser(party.getPartyId(), tgUser1);
    partyService.addUser(party.getPartyId(), tgUser2);
    partyService.addUser(party.getPartyId(), tgUser3);
    partyService.addUser(party.getPartyId(), tgUser4);
    partyService.addUser(party.getPartyId(), tgUser5);

    paymentService.addPayment(party.getPartyId(), tgUser1, BigDecimal.valueOf(620), "За баньку");
    paymentService.addPayment(party.getPartyId(), tgUser3, BigDecimal.valueOf(380), "За пиццку");

    transferService.send(chat.getTgChatId(), 2L, "@Username1", BigDecimal.valueOf(50));
    transferService.send(chat.getTgChatId(), 3L, "@Username1", BigDecimal.valueOf(50));
    transferService.send(chat.getTgChatId(), 4L, "@Username1", BigDecimal.valueOf(50));
    transferService.send(chat.getTgChatId(), 5L, "@Username1", BigDecimal.valueOf(50));

    PartyInfo partyInfo = partyInfoService.getPartyInfo(5L);
    assertEquals(5, partyInfo.getPartyUserInfoMap().size());
    assertEquals(0,  BigDecimal.valueOf(1000).compareTo(partyInfo.getTotal()));
    assertEquals(0, BigDecimal.valueOf(200.00).compareTo(partyInfo.getMiddleValue()));
  }

  private TgUser makeUser(long id, String username) {
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(id);
    tgUser.setUserName(username);
    return tgUserService.saveUser(tgUser);
  }
}