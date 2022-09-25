package ru.rohtuasad.chipin.core.payment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.user.model.User;

public interface PaymentService {

  Payment addPayment(UUID partyId, User user, BigDecimal amount, String description);

  List<Payment> getPartyPayments(UUID partyId);
}
