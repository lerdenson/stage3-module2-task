package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ReadAllNewsCommand implements Command {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private Map<String, String> params;

    @Autowired
    public ReadAllNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller) {
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
        List<NewsDtoResponse> responseList = controller.readAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (NewsDtoResponse news : responseList) {
            stringBuilder.append(news.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
