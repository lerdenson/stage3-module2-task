package com.mjc.school;

import com.mjc.school.utils.OperationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class App {
    private final OperationHandler operationHandler;

    @Autowired
    public App(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            operationHandler.printMenu();
            String input = scanner.nextLine();
            operationHandler.handleOperation(input, scanner);
        }
    }
}
