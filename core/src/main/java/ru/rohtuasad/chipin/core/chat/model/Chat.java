package ru.rohtuasad.chipin.core.chat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
public class Chat implements Persistable<Long> {
  @Id
  private Long tgChatId;
  private String tgChatName;
  @Transient
  private boolean isNew;

  @Override
  public Long getId() {
    return tgChatId;
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }

  public void setIsNew(boolean value) {
    this.isNew = value;
  }
}
