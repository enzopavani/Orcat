package io.github.enzopavani.orcat.controller;

import io.github.enzopavani.orcat.controller.dto.AuthorDTO;
import io.github.enzopavani.orcat.controller.mapper.AuthorMapper;
import io.github.enzopavani.orcat.model.Author;
import io.github.enzopavani.orcat.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController implements GenericController {

    private final AuthorService service;
    private final AuthorMapper mapper;

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
        Optional<Author> optionalAuthor = service.findById(authorId);
        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(optionalAuthor.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid AuthorDTO dto) {
        UUID authorId = UUID.fromString(id);
        Optional<Author> optionalAuthor = service.findById(authorId);
        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Author author = optionalAuthor.get();
        author.setName(dto.name());
        author.setEmail(dto.email());
        author.setPassword(dto.password());
        author.setNationality(dto.nationality());
        author.setBirthdate(dto.birthdate());
        service.update(author);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> pageSearch(
            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "email", required = false)
            String email,
            @RequestParam(value = "nationality", required = false)
            String nationality,
            @RequestParam(value = "birthdate-year", required = false)
            Integer birthdateYear,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "5")
            Integer pageSize
    ) {
        Page<Author> authorPage = service.search(name, email, nationality, birthdateYear, page, pageSize);
        Page<AuthorDTO> dtoPage = authorPage.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }
}
