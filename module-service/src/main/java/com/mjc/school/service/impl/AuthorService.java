package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.ToValidate;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mappers.AuthorMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.authorRepository = repository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorMapper.modelListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorOptional = authorRepository.readById(id);
        if (authorOptional.isPresent()) return authorMapper.modelToDto(authorOptional.get());
        else throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toMsg(), id));
    }

    @Override
    @ToValidate
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorMapper.modelToDto(authorRepository.create(authorMapper.dtoToModel(createRequest)));
    }

    @Override
    @ToValidate
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        checkAuthorExist(updateRequest.getId());
        return authorMapper.modelToDto(
                authorRepository.update(
                        authorMapper.dtoToModel(updateRequest)
                )
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }

    private void checkAuthorExist(Long id) {
        if (!authorRepository.existById(id))
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toMsg(), id));
    }
}
