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
        entity.setCreateDate(LocalDateTime.now());
        entity.setLastUpdateDate(LocalDateTime.now());
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

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        Optional<AuthorModel> optionalAuthorModel = readById(id);
        if (optionalAuthorModel.isPresent()) {
            List<AuthorModel> authorsList = dataSource.getAuthors();
            return authorsList.remove(optionalAuthorModel.get());
        } else return false;

    }

    @Override
    public boolean existById(Long id) {
        List<AuthorModel> authorsList = dataSource.getAuthors();
        return authorsList.stream().anyMatch(a -> a.getId().equals(id));
    }
}
