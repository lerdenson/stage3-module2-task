package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.utils.Utils.getLinesFromFile;

@Component
public class AuthorData {
    private static final String AUTHORS_FILE_NAME = "authors";
    private static long id = 1;
    private List<AuthorModel> authorsList;

    public AuthorData() {
        init();
    }

    public List<AuthorModel> getAuthorsList() {
        return authorsList;
    }

    public static long getNextId() {
        return id++;
    }

    private void init() {
        authorsList = new ArrayList<>();
        List<String> authorNames = getLinesFromFile(AUTHORS_FILE_NAME);
        for (String authorName : authorNames) {
            authorsList.add(
                    new AuthorModel(
                            getNextId(),
                            authorName,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ));
        }
    }


}
