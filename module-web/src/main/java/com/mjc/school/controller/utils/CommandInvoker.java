package com.mjc.school.controller.utils;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.dto.RequestDto;
import com.mjc.school.controller.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandInvoker {
    private final Map<String, Command> commandsMap;

    @Autowired
    public CommandInvoker(Map<String, Command> commandsMap) {
        this.commandsMap = commandsMap;
    }

    @CommandHandler(operation = "1")
    public ResponseDto getNews(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("readAllNewsCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "2")
    public ResponseDto getNewsById(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("readNewsByIdCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "3")
    public ResponseDto addNews(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("addNewsCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "4")
    public ResponseDto updateNews(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("updateNewsCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "5")
    public ResponseDto deleteNews(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("deleteNewsCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "6")
    public ResponseDto getAuthors(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("readAllAuthorsCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "7")
    public ResponseDto getAuthorById(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("readAuthorByIdCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "8")
    public ResponseDto addAuthor(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("addAuthorCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "9")
    public ResponseDto updateAuthor(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("updateAuthorCommand").setParams(request.getParams()).execute()
        );
    }

    @CommandHandler(operation = "10")
    public ResponseDto deleteAuthor(RequestDto request) {
        return new ResponseDto(
                commandsMap.get("deleteAuthorCommand").setParams(request.getParams()).execute()
        );
    }
}
