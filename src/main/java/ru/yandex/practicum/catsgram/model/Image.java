package ru.yandex.practicum.catsgram.model;

@lombok.Data
@lombok.EqualsAndHashCode(of = {"id"})
public class Image {
    Long id;
    long postId;
    String originalFileName;
    String filePath;
}
