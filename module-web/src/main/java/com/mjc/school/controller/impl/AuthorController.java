package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @Override
    @CommandHandler
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    @CommandHandler
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
