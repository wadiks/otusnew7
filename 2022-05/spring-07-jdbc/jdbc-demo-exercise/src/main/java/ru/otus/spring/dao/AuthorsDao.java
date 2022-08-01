package ru.otus.spring.dao;

import ru.otus.spring.model.Authors;

import java.util.List;

public interface AuthorsDao {

    int count();

    Authors getById(long id);

    List<Authors> getAll();

}
