package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.controller.dto.InPostDTO;
import io.github.enzopavani.orcat.controller.mapper.PostMapper;
import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController implements GenericController {

    private final PostService service;
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
}
