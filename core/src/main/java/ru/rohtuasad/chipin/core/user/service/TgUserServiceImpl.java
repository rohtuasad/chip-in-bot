package ru.rohtuasad.chipin.core.user.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.user.model.TgUser;
import ru.rohtuasad.chipin.core.user.repository.TgUserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TgUserServiceImpl implements TgUserService {

  private final TgUserRepository tgUserRepository;

  @Override
  @Nullable
  public TgUser getUser(long id) {
    final Optional<TgUser> user = tgUserRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }
    log.warn("Не удалось найти пользователя с uuid = {}", id);
    return null;
  }

  @Override
  public List<TgUser> getUsers(List<Long> ids) {
    return tgUserRepository.findAllByUserTgIdIn(ids);
  }

  @Override
  public TgUser saveUser(TgUser tgUser) {
    if (tgUser.getId() != null) {
      // Нужно проверить по userTgId, возможно такой уже есть в базе
      final TgUser byTgUserId = getUser(tgUser.getId());
      if (byTgUserId != null) {
        tgUser.setIsNew(false);
        return tgUserRepository.save(tgUser);
      }
    }
    tgUser.setIsNew(true);
    return tgUserRepository.save(tgUser);
  }
}
