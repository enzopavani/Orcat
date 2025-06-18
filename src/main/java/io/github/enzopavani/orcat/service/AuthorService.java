package io.github.enzopavani.orcat.service;

import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Optional<Author> findById(UUID id) {
        return repository.findById(id);
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public void delete(Author author) {
        repository.delete(author);
    }

    public void update(Author author) {
        if(author.getId() == null) {
            throw new IllegalArgumentException("Esse autor não existe na base de dados");
        }
        repository.save(author);
    }
}
