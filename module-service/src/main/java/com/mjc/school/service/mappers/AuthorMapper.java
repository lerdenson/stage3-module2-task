package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface AuthorMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> authorModelList);

    AuthorDtoResponse modelToDto(AuthorModel authorModel);

    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdatedDate", ignore = true)
    })
    AuthorModel dtoToModel(AuthorDtoRequest authorDtoRequest);
}
