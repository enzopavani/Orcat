package io.github.enzopavani.orcat.repository.specs;

import io.github.enzopavani.orcat.model.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecs {

    public static Specification<Author> nameLike(String name) {
        return ((root, query, cb) ->
                cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
    }

    public static Specification<Author> emailLike(String email) {
        return ((root, query, cb) ->
                cb.like(cb.upper(root.get("email")), "%" + email.toUpperCase() + "%"));
    }

    public static Specification<Author> nationalityLike(String nationality) {
        return ((root, query, cb) ->
                cb.like(cb.upper(root.get("nationality")), "%" + nationality.toUpperCase() + "%"));
    }

    public static Specification<Author> birthdateYearEqual(Integer birthdateYear) {
        return ((root, query, cb) ->
                cb.equal(cb.function("to_char", String.class,
                        root.get("birthdate"), cb.literal("YYYY")), birthdateYear.toString()));
    }
}
