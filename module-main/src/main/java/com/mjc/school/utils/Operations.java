package com.mjc.school.utils;

import com.mjc.school.exception.WrongInputException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Operations {
    GET_ALL_NEWS("1", "Get all news."),
    GET_NEWS_BY_ID("2", "Get news by id."),
    CREATE_NEWS("3", "Create news."),
    UPDATE_NEWS("4", "Update news."),
    REMOVE_NEWS_BY_ID("5", "Remove news by id."),
    GET_ALL_AUTHORS("6", "Get all authors."),
    GET_AUTHOR_BY_ID("7", "Get author by id."),
    CREATE_AUTHOR("8", "Create author."),
    UPDATE_AUTHOR("9", "Update author."),
    REMOVE_AUTHOR_BY_ID("10", "Remove author by id."),
    EXIT("0", "Exit.");

    private final String commandNumber;
    private final String description;

    Operations(String command, String description) {
        this.commandNumber = command;
        this.description = description;
    }

    public static Operations getCommand(String commandNumber) throws WrongInputException {
        Optional<Operations> optional = Arrays.stream(Operations.values()).filter(a -> Objects.equals(a.commandNumber, commandNumber)).findFirst();
        if (optional.isPresent()) return optional.get();
        else throw new WrongInputException("Command not found");
    }

    public String getCommandNumber() {
        return commandNumber;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", commandNumber, description);
    }
}
