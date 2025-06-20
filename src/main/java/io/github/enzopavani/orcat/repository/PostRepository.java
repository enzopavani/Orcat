package io.github.enzopavani.orcat.repository;

import io.github.enzopavani.orcat.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>, JpaSpecificationExecutor<Post> {
}
