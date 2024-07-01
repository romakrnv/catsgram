package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts")
    public Collection<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping(value = "/post/{id}")
    public Post findPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping("/post")
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}