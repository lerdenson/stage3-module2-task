package com.mjc.school.controller.commands.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UpdateAuthorCommand implements Command {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;
    private Map<String, String> params;

    @Autowired
    public UpdateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller) {
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
        try {
            AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(
                    Long.parseLong(params.get("id")),
                    params.get("name")
            );
            AuthorDtoResponse response = controller.update(authorDtoRequest);
            return response.toString();

        } catch (ValidationException | NotFoundException e) {
            return e.getMessage();
        }
    }
}
