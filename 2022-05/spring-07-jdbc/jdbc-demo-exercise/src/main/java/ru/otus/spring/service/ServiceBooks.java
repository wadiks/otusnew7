package ru.otus.spring.service;

import ru.otus.spring.model.Books;

import java.util.List;
import java.util.Optional;

public interface ServiceBooks {
    Long count();

    Books save(Books books);

    void Update( int number, String name);

    Optional<Books> getById(long id);

    List<Books> getAll();

    void deleteById(int number);


}
