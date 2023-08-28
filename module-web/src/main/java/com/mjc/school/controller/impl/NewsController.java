package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    @CommandHandler
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    @CommandHandler
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    @CommandHandler
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @CommandHandler
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @CommandHandler
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
