package ru.otus.spring.dao;

import ru.otus.spring.model.Books;

import java.util.List;
import java.util.Optional;

public interface BooksDao {

    Long count();

    Books insert(Books books);

    Optional<Books> getById(long id);

    List<Books> getAll();

    void deleteById(long id);

    void update(Books books);
}
