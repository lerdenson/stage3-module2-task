package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.data.AuthorData;
import com.mjc.school.repository.model.data.NewsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSource {
    private AuthorData authorData;
    private NewsData newsData;

    @Autowired
    public DataSource(AuthorData authorData, NewsData newsData) {
        this.authorData = authorData;
        this.newsData = newsData;
    }

    public List<AuthorModel> getAuthors() {
        return authorData.getAuthorsList();
    }

    public List<NewsModel> getNews() {
        return newsData.getNewsList();
    }

    public long getNextAuthorId() {
        return authorData.getNextId();
    }

    public long getNextNewsId() {
        return newsData.getNextId();
    }
}
