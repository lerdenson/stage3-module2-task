package com.mjc.school.controller.commands.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeleteAuthorCommand implements Command {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;

    private Map<String, String> params;

    @Autowired
    public DeleteAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller) {
        this.controller = controller;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Command setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public String execute() {
        Long id = Long.parseLong(params.get("id"));
        return Boolean.toString(
                controller.deleteById(id)
        );
    }
}
