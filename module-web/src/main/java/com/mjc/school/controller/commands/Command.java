package com.mjc.school.controller.commands;

import java.util.Map;

public interface Command {
    Command setParams(Map<String, String> params);
    Map<String, String> getParams();
    String execute();
}
