package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Books;

import java.util.List;

public interface BooksDao extends CrudRepository<Books, Long> {

    List<Books> findAll();

}
