package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.ToValidate;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mappers.NewsMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsMapper.modelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsOptional = newsRepository.readById(id);
        if (newsOptional.isPresent()) return newsMapper.modelToDto(newsOptional.get());
        else throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toMsg(), id));
    }

    @ToValidate
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsMapper.modelToDto(newsRepository.create(newsMapper.dtoToModel(createRequest)));
    }

    @ToValidate
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        checkNewsExist(updateRequest.getId());
        return newsMapper.modelToDto(
                newsRepository.update(
                        newsMapper.dtoToModel(updateRequest)
                )
        );

    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

    private void checkNewsExist(Long id) {
        if (!newsRepository.existById(id)) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toMsg(), id));
        }
    }
}
