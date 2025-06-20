package io.github.enzopavani.orcat.controller.mapper;

import io.github.enzopavani.orcat.controller.dto.InPostDTO;
import io.github.enzopavani.orcat.controller.dto.OutPostDTO;
import io.github.enzopavani.orcat.model.Post;
import io.github.enzopavani.orcat.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class PostMapper {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;

    @Mapping(target = "originalAuthor",
            expression = "java( authorMapper.toEntity(authorMapper.toDTO(authorRepository.findById(dto.authorId()).orElse(null))) )")
    public abstract Post toEntity(InPostDTO dto);

    public abstract OutPostDTO toDTO(Post post);
}
