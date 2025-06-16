package io.github.enzopavani.orcat.service;

import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author save(Author author) {
        return repository.save(author);
    }
}
