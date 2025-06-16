package io.github.enzopavani.orcat.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
    UUID id,

    @NotBlank(message = "Campo obrigatório")
    @Size(message = "Campo deve possuir entre 2 e 150 caracteres", min = 2, max = 150)
    String name,

    @NotBlank(message = "Campo obrigatório")
    @Size(message = "Campo deve possuir entre 8 e 100 caracteres", min = 8, max = 100)
    String email,

    @NotBlank(message = "Campo obrigatório")
    @Size(message = "Campo deve possuir entre 6 e 20 caracteres", min = 6, max = 20)
    String password,

    @NotBlank(message = "Campo obrigatório")
    @Size(message = "Campo deve possuir entre 2 e 100 caracteres", min = 2, max = 100)
    String nationality,

    @NotNull(message = "Campo obrigatório")
    @Past(message = "Data deve ser anterior a atual")
    LocalDate birthdate
) {
}
