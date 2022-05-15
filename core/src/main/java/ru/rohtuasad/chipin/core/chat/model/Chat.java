package ru.rohtuasad.chipin.core.chat.model;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Chat {
  @Id
  private UUID chatId;
  private String tgChatId;
  private String tgChatName;
}
