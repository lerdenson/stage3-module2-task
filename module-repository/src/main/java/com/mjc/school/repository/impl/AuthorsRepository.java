package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotations.OnDelete;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsRepository implements BaseRepository<AuthorModel, Long> {
    private final DataSource dataSource;

    @Autowired
    public AuthorsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> authorList = dataSource.getAuthors();
        entity.setId(dataSource.getNextAuthorId());
        authorList.add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = readById(entity.getId()).get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(LocalDateTime.now());
        return authorModel;


    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        AuthorModel author = readById(id).get();
        return dataSource.getAuthors().remove(author);
    }

    @Override
    public boolean existById(Long id) {
        List<AuthorModel> authorsList = dataSource.getAuthors();
        return authorsList.stream().anyMatch(a -> a.getId().equals(id));
    }
}
