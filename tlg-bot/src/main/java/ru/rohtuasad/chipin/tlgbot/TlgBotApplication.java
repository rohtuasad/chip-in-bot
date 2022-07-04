package ru.rohtuasad.chipin.tlgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.rohtuasad.chipin.core.annotation.EnableChipInCore;

@SpringBootApplication
@EnableChipInCore
public class TlgBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(TlgBotApplication.class, args);
  }

}
