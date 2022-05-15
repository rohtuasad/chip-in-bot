package ru.rohtuasad.chipin.core.chat.model;

import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@RequiredArgsConstructor
@Data
@Table("chat")
public class Chat {
  @Id
  @Column("chat-id")
  private UUID id;
  private String tgChatId;
  private String tgChatName;
}
