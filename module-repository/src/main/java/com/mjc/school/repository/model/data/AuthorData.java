package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.utils.Utils.getRandomLineFromFile;

@Component
public class AuthorData {
    private static final String AUTHORS_FILE_NAME = "authors";
    private List<AuthorModel> authorsList;
    private static long id = 1;

    public AuthorData() {
        init();
    }

    public List<AuthorModel> getAuthorsList() {
        return authorsList;
    }

    public long getNextId() {
        return id++;
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            authorsList = new ArrayList<>();
            authorsList.add(
                    new AuthorModel(
                            getNextId(),
                            getRandomLineFromFile(AUTHORS_FILE_NAME),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ));
        }
    }


}
