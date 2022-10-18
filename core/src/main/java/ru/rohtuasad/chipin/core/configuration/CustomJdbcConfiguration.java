package ru.rohtuasad.chipin.core.configuration;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.payment.model.Payment;
import ru.rohtuasad.chipin.core.transfer.model.Transfer;

@Configuration
public class CustomJdbcConfiguration {

  @Bean
  public NamingStrategy namingStrategy() {
    return new NamingStrategy() {
      @Override
      @NonNull
      public String getSchema() {
        return "chip_in";
      }
    };
  }

  @Bean
  public BeforeConvertCallback<Party> partyIdGenerator() {
    return (party) -> {
      if (party.getPartyId() == null) {
        party.setPartyId(UUID.randomUUID());
      }
      return party;
    };
  }

  @Bean
  public BeforeConvertCallback<Payment> paymentIdGenerator() {
    return (payment) -> {
      if (payment.getPaymentId() == null) {
        payment.setPaymentId(UUID.randomUUID());
      }
      return payment;
    };
  }

  @Bean
  public BeforeConvertCallback<Transfer> transferIdGenerator() {
    return (transfer) -> {
      if (transfer.getTransferId() == null) {
        transfer.setTransferId(UUID.randomUUID());
      }
      return transfer;
    };
  }
}
