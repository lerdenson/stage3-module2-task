package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static String getRandomLineFromFile(String filename) {
        List<String> content = new ArrayList<>();
        Random random = new Random();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Utils.class.getClassLoader().getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
            return content.get(random.nextInt(content.size()));

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
