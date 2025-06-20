package io.github.enzopavani.orcat.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record InPostDTO(
        @NotBlank(message = "Campo obrigatório")
        @Size(message = "Campo deve possuir entre 1 e 100 caracteres", min = 1, max = 100)
        String title,

        @Size(message = "Campo deve possuir no máximo 500 caracteres", max = 500)
        String description,

        @NotNull(message = "Campo obrigatório")
        UUID authorId
) {
}
