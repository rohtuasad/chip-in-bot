package ru.rohtuasad.chipin.core.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rohtuasad.chipin.core.chat.model.Chat;
import ru.rohtuasad.chipin.core.user.model.User;
import ru.rohtuasad.chipin.core.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  @Nullable
  public User getUser(long id) {
    final Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }
    log.warn("Не удалось найти пользователя с uuid = {}", id);
    return null;
  }

  @Override
  public User saveUser(User user) {
    if (user.getId() != null) {
      // Нужно проверить по userTgId, возможно такой уже есть в базе
      final User byUserId = getUser(user.getId());
      if (byUserId != null) {
        user.setIsNew(false);
        return userRepository.save(user);
      }
    }
    user.setIsNew(true);
    return userRepository.save(user);
  }
}
