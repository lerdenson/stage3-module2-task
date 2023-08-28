package com.mjc.school.controller.dto;

public class ResponseDto {
    private String result;

    public ResponseDto(String result) {
        this.result = result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}
