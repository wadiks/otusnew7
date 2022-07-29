package ru.otus.spring.dao;

import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Long count();

    Optional<Genre> getById(long id);

    List<Genre> getAll();

}
