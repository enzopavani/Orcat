package io.github.enzopavani.orcat.controller.mapper;

import io.github.enzopavani.orcat.controller.dto.AuthorDTO;
import io.github.enzopavani.orcat.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);
}
