package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

@lombok.Data
@lombok.EqualsAndHashCode(of = {"id"})
public class Post {
    Long id;
    long authorId;
    String description;
    Instant postDate;
}
