package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.controller.dto.AuthorDTO;
import io.github.enzopavani.orcat.controller.mapper.AuthorMapper;
import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController implements GenericController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        List<Author> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AuthorDTO dto) {
        Author author = mapper.toEntity(dto);
        service.save(author);
        URI location = generateHeaderLocation(author.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        UUID authorId = UUID.fromString(id);
        Optional<Author> author = service.findById(authorId);
        if(author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(author.get());
        return ResponseEntity.noContent().build();
    }
}
