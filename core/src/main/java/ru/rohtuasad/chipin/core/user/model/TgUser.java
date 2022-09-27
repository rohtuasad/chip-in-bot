package ru.rohtuasad.chipin.core.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
public class TgUser implements Persistable<Long> {
  @Id
  private Long userTgId;
  private String nickName;
  private String userName;

  @Transient
  private boolean isNew;

  @Override
  public Long getId() {
    return userTgId;
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }

  public void setIsNew(boolean value) {
    this.isNew = value;
  }
}
