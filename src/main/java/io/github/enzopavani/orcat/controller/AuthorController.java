package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>> listAll() {
        List<Author> list = service.listAuthors();
        return ResponseEntity.ok(list);
    }
}
