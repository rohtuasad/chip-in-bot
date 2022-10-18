package ru.rohtuasad.chipin.core.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.party.model.Party;
import ru.rohtuasad.chipin.core.party.model.PartyUser;
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
  public TgUser getUser(String userName) {
    final Optional<TgUser> user = tgUserRepository.findByUserName(userName);
    if (user.isPresent()) {
      return user.get();
    }
    throw new IllegalArgumentException("Не удалось найти пользователя с юнернеймом = " + userName);
  }

  @Override
  public List<TgUser> getUsers(Party party) {
    List<Long> userIds = party.getUsers().stream().map(PartyUser::getUserId)
        .collect(Collectors.toList());
    return tgUserRepository.findAllByUserTgIdIn(userIds);
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
