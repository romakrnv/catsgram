package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private static long globalId = 0;

    public Collection<User> getUsers() {
        return users.values();
    }

    public User getUser(Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found.");
        }
        return user;
    }

    public User create(User user) {
        if (user.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }
        if (checkEmail(user)) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }
        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());
        users.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователь с id = " + user.getId() + " не найден");
        }
        User oldUser = users.get(user.getId());
        if (!user.getEmail().equals(users.get(user.getId()).getEmail())) {
            if (checkEmail(user)) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }
            oldUser.setEmail(user.getEmail());
        }
        if (!(user.getPassword() == null)) {
            oldUser.setPassword(user.getPassword());
        }
        if (!(user.getUsername() == null)) {
            oldUser.setUsername(user.getUsername());
        }
        return oldUser;
    }

    private boolean checkEmail(User user) {
        return users.values().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    private long getNextId() {
        return globalId++;
    }

    public Optional<User> findUserById(Long id) {
        return Optional.of(users.get(id));
    }
}
