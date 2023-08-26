package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class DeleteAspect {
    DataSource dataSource;


    @Autowired
    public DeleteAspect(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @AfterReturning("@annotation(com.mjc.school.repository.annotations.OnDelete) && args(id)")
    public void onAuthorDelete(Long id) {
        List<NewsModel> listToDelete = dataSource.getNews().stream().filter(a -> a.getAuthorId().equals(id)).toList();
        dataSource.getNews().removeAll(listToDelete);
    }
}
