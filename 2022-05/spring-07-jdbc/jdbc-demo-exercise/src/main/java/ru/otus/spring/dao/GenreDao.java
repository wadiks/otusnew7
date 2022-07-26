package ru.otus.spring.dao;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    Genre getById(long id);

    List<Genre> getAll();

}
