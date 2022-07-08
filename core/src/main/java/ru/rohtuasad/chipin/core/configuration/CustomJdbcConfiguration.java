package ru.rohtuasad.chipin.core.configuration;

import java.util.UUID;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.lang.NonNull;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.party.model.Party;

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
  public ApplicationListener<BeforeSaveEvent<?>> partyIdGenerator() {
    return event -> {
      var entity = event.getEntity();
      if (entity instanceof Party) {
        ((Party) entity).setPartyId(UUID.randomUUID());
      }
    };
  }
}
