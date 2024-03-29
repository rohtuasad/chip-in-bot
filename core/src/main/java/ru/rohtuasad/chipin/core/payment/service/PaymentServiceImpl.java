package ru.rohtuasad.chipin.core.payment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.payment.repository.PaymentRepository;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.service.TgUserService;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final TgUserService tgUserService;

  @Override
  public Payment addPayment(UUID partyId, TgUser tgUser, BigDecimal amount, String description) {
    tgUserService.saveUser(tgUser);
    Payment payment = new Payment();
    payment.setPartyId(partyId);
    payment.setUserId(tgUser.getUserTgId());
    payment.setAmount(amount);
    payment.setDescription(description);
    return paymentRepository.save(payment);
  }

  @Override
  public List<Payment> getPartyPayments(UUID partyId) {
    return paymentRepository.findAllByPartyId(partyId);
  }
}
