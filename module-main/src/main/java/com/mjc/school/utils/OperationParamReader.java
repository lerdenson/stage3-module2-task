package com.mjc.school.utils;

import com.mjc.school.controller.dto.RequestDto;
import com.mjc.school.exception.WrongInputException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationParamReader {
    private final String WRONG_ID_MESSAGE = "id must be digital";

    public RequestDto readCommandParams(Operations operation, Scanner scanner) throws WrongInputException {
        switch (operation) {
            case GET_ALL_NEWS, GET_ALL_AUTHORS -> {
                return new RequestDto();
            }
            case REMOVE_NEWS_BY_ID, REMOVE_AUTHOR_BY_ID, GET_NEWS_BY_ID, GET_AUTHOR_BY_ID -> {
                return readId(scanner);
            }
            case CREATE_NEWS -> {
                return addNews(scanner);
            }
            case UPDATE_NEWS -> {
                return updateNews(scanner);
            }
            case CREATE_AUTHOR -> {
                return addAuthor(scanner);
            }
            case UPDATE_AUTHOR -> {
                return updateAuthor(scanner);
            }
            case EXIT -> System.exit(0);

            default -> throw new WrongInputException("Command not found");
        }
        return null;
    }

    private RequestDto readId(Scanner scanner) throws WrongInputException {
        String id = readString(scanner, "id");
        if (isLong(id)) {
            Map<String, String> params = new HashMap<>();
            params.put("id", id);
            return new RequestDto(params);
        } else throw new WrongInputException(WRONG_ID_MESSAGE);
    }

    private RequestDto addNews(Scanner scanner) throws WrongInputException {
        Map<String, String> params = new HashMap<>();
        return getGeneralNewsData(scanner, params);
    }

    private RequestDto addAuthor(Scanner scanner) {
        Map<String, String> params = new HashMap<>();
        params.put("name", readString(scanner, "author name"));
        return new RequestDto(params);
    }

    private RequestDto updateNews(Scanner scanner) throws WrongInputException {
        Map<String, String> params = new HashMap<>();

        String id = readString(scanner, "id");
        if (!isLong(id)) throw new WrongInputException(WRONG_ID_MESSAGE);
        params.put("id", id);

        return getGeneralNewsData(scanner, params);
    }


    private RequestDto updateAuthor(Scanner scanner) throws WrongInputException {
        Map<String, String> params = new HashMap<>();

        String id = readString(scanner, "id");
        if (!isLong(id)) throw new WrongInputException(WRONG_ID_MESSAGE);
        params.put("id", id);

        params.put("name", readString(scanner, "name"));

        return new RequestDto(params);
    }

    private RequestDto getGeneralNewsData(Scanner scanner, Map<String, String> params) throws WrongInputException {
        params.put("title", readString(scanner, "title"));
        params.put("content", readString(scanner, "content"));
        String authorId = readString(scanner, "author id");
        if (!isLong(authorId)) throw new WrongInputException(WRONG_ID_MESSAGE);

        params.put("authorId", authorId);

        return new RequestDto(params);
    }

    private String readString(Scanner scanner, String paramName) {
        System.out.format("Enter %s:", paramName);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());

        return input;
    }

    private boolean isLong(String string) {
        return string.matches("\\d*");
    }
}
