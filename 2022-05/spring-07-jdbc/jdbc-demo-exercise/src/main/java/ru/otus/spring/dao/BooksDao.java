package ru.otus.spring.dao;

import ru.otus.spring.model.Books;

import java.util.List;

public interface BooksDao {

    int count();

    void insert(Books books);

    Books getById(long id);

    List<Books> getAll();

    void deleteById(long id);

    void update(Books books);
}
