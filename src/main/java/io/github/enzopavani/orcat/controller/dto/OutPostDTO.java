package io.github.enzopavani.orcat.controller.dto;

import java.util.UUID;

public record OutPostDTO(
        UUID id,
        String title,
        String description,
        AuthorDTO dto
) {
}
