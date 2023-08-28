package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddNewsCommand implements Command {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;

    private Map<String, String> params;

    @Autowired
    public AddNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller) {
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
            NewsDtoRequest newsDtoRequest = new NewsDtoRequest(
                    params.get("title"),
                    params.get("content"),
                    Long.parseLong(params.get("authorId")));
            NewsDtoResponse response = controller.create(newsDtoRequest);
            return response.toString();
        } catch (ValidationException | NotFoundException e) {
            return e.getMessage();
        }


    }
}
