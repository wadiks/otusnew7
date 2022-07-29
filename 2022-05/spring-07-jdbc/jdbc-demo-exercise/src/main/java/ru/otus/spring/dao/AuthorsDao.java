package ru.otus.spring.dao;

import ru.otus.spring.model.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {

    Long count();

    Optional<Authors> getById(long id);

    List<Authors> getAll();

}
