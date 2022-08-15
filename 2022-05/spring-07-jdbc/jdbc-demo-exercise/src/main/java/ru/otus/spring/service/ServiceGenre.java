package ru.otus.spring.service;

import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Optional;

public interface ServiceGenre {

    Long count();

    Optional<Genre> getById(long id);

    List<Genre> getAll();

}
