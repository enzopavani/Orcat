package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.controller.dto.InPostDTO;
import io.github.enzopavani.orcat.controller.mapper.PostMapper;
import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.service.AuthorService;
import io.github.enzopavani.orcat.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController implements GenericController {

    private final PostService service;
    private final AuthorService authorService;
    private final PostMapper mapper;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid InPostDTO dto) {
        Post post = mapper.toEntity(dto);
        service.save(post);
        URI location = generateHeaderLocation(post.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        UUID postId = UUID.fromString(id);
        Optional<Post> optionalPost = service.findById(postId);
        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(optionalPost.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid InPostDTO dto) {
        UUID postId = UUID.fromString(id);
        Optional<Post> optionalPost = service.findById(postId);
        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        post.setTitle(dto.title());
        post.setDescription(dto.description());
        post.setOriginalAuthor(authorService.findById(dto.authorId()).orElse(null));
        service.update(post);
        return ResponseEntity.noContent().build();
    }
}
