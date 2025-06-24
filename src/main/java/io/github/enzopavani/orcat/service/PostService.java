package io.github.enzopavani.orcat.service;

import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.repository.PostRepository;
import io.github.enzopavani.orcat.repository.specs.PostSpecs;
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
public class PostService {

    private final PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Optional<Post> findById(UUID id) {
        return repository.findById(id);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void delete(Post post) {
        repository.delete(post);
    }

    public void update(Post post) {
        if(post.getId() == null) {
            throw new IllegalArgumentException("Esse post não existe na base de dados");
        }
        repository.save(post);
    }

    public Page<Post> search(
        String title,
        String description,
        String authorId,
        Integer page,
        Integer pageSize
    ) {
        Specification<Post> specs = Specification.where((root, query, cb) -> cb.conjunction());
        if(title != null) {
            specs = specs.and(PostSpecs.titleLike(title));
        }
        if(description != null) {
            specs = specs.and(PostSpecs.descriptionLike(description));
        }
        if(authorId != null) {
            specs = specs.and(PostSpecs.authorIdEqual(UUID.fromString(authorId)));
        }
        Pageable pageRequest = PageRequest.of(page, pageSize);
        return repository.findAll(specs, pageRequest);
    }
}
