package io.github.enzopavani.orcat.service;

import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.repository.AuthorRepository;
import io.github.enzopavani.orcat.repository.specs.AuthorSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Author> search(
        String name,
        String email,
        String nationality,
        Integer birthdateYear,
        Integer page,
        Integer pageSize
    ) {
        Specification<Author> specs = Specification.where((root, query, cb) -> cb.conjunction());
        if(name != null) {
            specs = specs.and(AuthorSpecs.nameLike(name));
        }
        if(email != null) {
            specs = specs.and(AuthorSpecs.emailLike(email));
        }
        if(nationality != null) {
            specs = specs.and(AuthorSpecs.nationalityLike(nationality));
        }
        if(birthdateYear != null) {
            specs = specs.and(AuthorSpecs.birthdateYearEqual(birthdateYear));
        }
        Pageable pageRequest = PageRequest.of(page, pageSize);
        return repository.findAll(specs, pageRequest);
    }
}
