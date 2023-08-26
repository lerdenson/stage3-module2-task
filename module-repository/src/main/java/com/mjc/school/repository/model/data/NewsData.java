package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.mjc.school.repository.utils.Utils.getRandomLineFromFile;

@Component
public class NewsData {
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private List<NewsModel> newsList;

    private static long id = 1;

    @Autowired
    public NewsData(AuthorData authorData) {
        init(authorData.getAuthorsList());
    }

    public long getNextId() {
        return id++;
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }

    private void init(List<AuthorModel> authorsList) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            newsList.add(
                    new NewsModel(
                            getNextId(),
                            getRandomLineFromFile(NEWS_FILE_NAME),
                            getRandomLineFromFile(CONTENT_FILE_NAME),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            authorsList.get(random.nextInt(authorsList.size())).getId()

                    )
            );
        }
    }
}
