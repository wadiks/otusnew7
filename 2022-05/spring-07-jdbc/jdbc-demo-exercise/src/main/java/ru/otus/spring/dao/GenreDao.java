package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Long> {

    List<Genre> findAll();
}
