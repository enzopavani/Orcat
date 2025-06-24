package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.controller.dto.InPostDTO;
import io.github.enzopavani.orcat.controller.dto.OutPostDTO;
import io.github.enzopavani.orcat.controller.mapper.PostMapper;
import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.service.AuthorService;
import io.github.enzopavani.orcat.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController implements GenericController {

    private final PostService service;
    private final AuthorService authorService;
    private final PostMapper mapper;

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

    @GetMapping
    public ResponseEntity<Page<OutPostDTO>> pageSearch(
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "description", required = false)
            String description,
            @RequestParam(value = "author-id", required = false)
            String authorId,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "5")
            Integer pageSize
    ) {
        Page<Post> postPage = service.search(title, description, authorId, page, pageSize);
        Page<OutPostDTO> dtoPage = postPage.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }
}
