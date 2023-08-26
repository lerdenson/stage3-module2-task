package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> newsList = dataSource.getNews();
        entity.setId(dataSource.getNextNewsId());
        newsList.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId()).get();
        newsModel.setTitle(entity.getTitle());
        newsModel.setContent(entity.getContent());
        newsModel.setAuthorId(entity.getAuthorId());
        newsModel.setLastUpdateDate(LocalDateTime.now());

        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        NewsModel newsModel = readById(id).get();
        List<NewsModel> newsList = dataSource.getNews();
        return newsList.remove(newsModel);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().stream().anyMatch(a -> a.getId().equals(id));
    }
}
