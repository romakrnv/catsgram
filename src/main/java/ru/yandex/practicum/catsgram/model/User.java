package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

@lombok.Data
@lombok.EqualsAndHashCode(of = {"email"})
public class User {
    Long id;
    String username;
    @lombok.NonNull
    String email;
    String password;
    Instant registrationDate;
}
