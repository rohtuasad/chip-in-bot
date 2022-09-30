package ru.rohtuasad.chipin.core.payment.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import ru.rohtuasad.chipin.core.payment.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, UUID> {
  List<Payment> findAllByPartyId(UUID partyId);
}
