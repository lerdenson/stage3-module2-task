package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReadNewsByIdCommand implements Command {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;

    private Map<String, String> params;

    @Autowired
    public ReadNewsByIdCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller) {
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
            NewsDtoResponse response = controller.readById(Long.parseLong(params.get("id")));
            return response.toString();
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }
}
