package com.mjc.school.utils;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.dto.RequestDto;
import com.mjc.school.controller.utils.CommandInvoker;
import com.mjc.school.exception.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@Component
public class OperationHandler {
    private final OperationParamReader commandReader;
    private final CommandInvoker commandInvoker;

    @Autowired
    public OperationHandler(OperationParamReader commandReader, CommandInvoker commandInvoker) {
        this.commandReader = commandReader;
        this.commandInvoker = commandInvoker;
    }

    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations operation : Operations.values()) {
            System.out.println(operation);
        }
    }

    public void handleOperation(String operationNumber, Scanner scanner) {
        try {
            Operations operation = Operations.getCommand(operationNumber);
            RequestDto requestDto = commandReader.readCommandParams(operation, scanner);
            execute(operation, requestDto);

        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private void execute(Operations operation, RequestDto request) {
        Method[] allMethods = CommandInvoker.class.getDeclaredMethods();
        Optional<Method> optionalMethod = Arrays.stream(allMethods)
                .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation.getCommandNumber()))
                .findFirst();
        if (optionalMethod.isEmpty()) {
            System.out.println("Command not found");
        } else {
            Method method = optionalMethod.get();
            try {
                System.out.println(
                        method.invoke(commandInvoker, request)
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
