package io.github.enzopavani.orcat.validator;

import io.github.enzopavani.orcat.exception.DuplicateRecordException;
import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository repository;

    public void validate(Author author) {
        if(emailExists(author)) {
            throw new DuplicateRecordException("Erro, este email já está em uso");
        }
    }

    private boolean emailExists(Author author) {
        Optional<Author> optionalAuthor = repository.findByEmail(author.getEmail());
        if(author.getId() == null) {
            return optionalAuthor.isPresent();
        }
        return optionalAuthor
                .map(Author::getId)
                .stream()
                .anyMatch(id -> !id.equals(author.getId()));
    }
}
