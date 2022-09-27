package ru.rohtuasad.chipin.core.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.service.PartyServiceImpl;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.user.model.TgUser;

@SpringBootTest()
@ActiveProfiles("test")
class PaymentServiceTest {

  @Autowired
  private PaymentService paymentService;
  @Autowired
  PartyServiceImpl partyService;

  @Test
  void addPayment() {
    TgUser tgUser = new TgUser();
    tgUser.setUserTgId(1L);
    tgUser.setUserName("@Username");
    tgUser.setNickName("Nickname");

    final Party activeParty = partyService.getActiveParty(1L);
    assertNotNull(activeParty);

    paymentService.addPayment(activeParty.getPartyId(), tgUser, BigDecimal.valueOf(1000),
        "For pizza");

    List<Payment> partyPayments = paymentService.getPartyPayments(activeParty.getPartyId());
    assertEquals(1, partyPayments.size());
    Payment payment = partyPayments.get(0);
    assertEquals("For pizza", payment.getDescription());
  }
}
