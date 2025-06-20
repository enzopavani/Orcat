package io.github.enzopavani.orcat.service;

import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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
}
