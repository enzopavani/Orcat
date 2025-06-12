package io.github.enzopavani.orcat.repository;

import io.github.enzopavani.orcat.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
