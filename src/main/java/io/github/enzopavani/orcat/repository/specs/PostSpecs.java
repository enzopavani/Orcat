package io.github.enzopavani.orcat.repository.specs;

import io.github.enzopavani.orcat.model.Post;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class PostSpecs {

    public static Specification<Post> titleLike(String title) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Post> descriptionLike(String description) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("description")), "%" + description.toUpperCase() + "%");
    }

    public static Specification<Post> authorIdEqual(UUID authorId) {
        return (root, query, cb) -> {
                Join<Object, Object> joinAuthor = root.join("originalAuthor", JoinType.LEFT);
                return cb.equal(joinAuthor.get("id"), authorId);
        };
    }
}
