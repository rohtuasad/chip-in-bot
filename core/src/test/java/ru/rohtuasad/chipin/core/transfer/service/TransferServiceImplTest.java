package ru.rohtuasad.chipin.core.transfer.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyService;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@SpringBootTest()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class TransferServiceImplTest {

  @Autowired
  PartyService partyService;
  @Autowired
  TgUserService tgUserService;
  @Autowired
  TransferService transferService;

  @Test
  @Order(1)
  void testPartyTransfers() {
    Party activeParty = partyService.getActiveParty(4L);

    List<Transfer> partyTransfers = transferService.getPartyTransfers(activeParty.getPartyId());
    assertEquals(0, partyTransfers.size());

    TgUser tgUser3 = new TgUser();
    tgUser3.setUserTgId(3L);
    tgUser3.setUserName("@Username3");
    tgUser3.setNickName("Nickname three");
    tgUserService.saveUser(tgUser3);

    TgUser tgUser4 = new TgUser();
    tgUser4.setUserTgId(4L);
    tgUser4.setUserName("@Username4");
    tgUser4.setNickName("Nickname four");
    tgUserService.saveUser(tgUser4);

    partyService.addUser(activeParty.getPartyId(), tgUser3);
    partyService.addUser(activeParty.getPartyId(), tgUser4);
    transferService.send(4L, 3L, "@Username4", BigDecimal.valueOf(1000.10));

    assertEquals(0, partyTransfers.size());
    partyTransfers = transferService.getPartyTransfers(activeParty.getPartyId());
    assertEquals(1, partyTransfers.size());
    assertEquals(3L, partyTransfers.get(0).getSenderId());
    assertEquals(4L, partyTransfers.get(0).getReceiverId());
    assertEquals(BigDecimal.valueOf(1000.10), partyTransfers.get(0).getAmount());
  }
}