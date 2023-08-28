package com.mjc.school.controller.dto;

import java.util.HashMap;
import java.util.Map;

public class RequestDto {
    private final Map<String, String> params;

    public RequestDto() {
        params = new HashMap<>();
    }

    public RequestDto(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
