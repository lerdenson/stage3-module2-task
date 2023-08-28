package com.mjc.school.controller.commands.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ReadAllAuthorsCommand implements Command {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;

    private Map<String, String> params;

    @Autowired
    public ReadAllAuthorsCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public Command setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public String execute() {
        List<AuthorDtoResponse> responseList = controller.readAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (AuthorDtoResponse author : responseList) {
            stringBuilder.append(author.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
