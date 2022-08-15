package ru.otus.spring.service;

import ru.otus.spring.model.Authors;

import java.util.List;
import java.util.Optional;

public interface ServiceAuthor {

    Long count();

    Optional<Authors> getById(long id);

    List<Authors> getAll();

}
