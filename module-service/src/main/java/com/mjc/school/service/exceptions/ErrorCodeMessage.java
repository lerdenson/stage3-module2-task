package com.mjc.school.service.exceptions;

public enum ErrorCodeMessage {
    NEWS_NOT_FOUND("01", "News with id: %d does not exist"),
    INCORRECT_NEWS_TITLE("02", "News title can not be less than 5 characters and more than 30. Your title length is: %d"),
    INCORRECT_NEWS_CONTENT("03", "Content can not be less than 5 characters and more than 255. Your content length is: %d"),
    AUTHOR_NOT_FOUND("04", "Author with id: %d does not exist"),
    INCORRECT_AUTHOR_NAME("05", "Author name can not be less than 3 characters and more than 15. Your name length is: %d"),
    NULL_AUTHOR_ID("06", "Author id can`t be null");

    private final String code;
    private final String message;

    ErrorCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String toMsg() {
        return "ERROR CODE: " + code + " , ERROR_MESSAGE: " + message;
    }
}
