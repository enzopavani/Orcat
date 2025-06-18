package io.github.enzopavani.orcat.repository;

import io.github.enzopavani.orcat.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>, JpaSpecificationExecutor<Author> {

    Optional<Author> findByEmail(String email);
}
