package ru.otus.spring.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Books;

public interface BooksDao extends CrudRepository<Books, Long> {
    List<Books> findAll();
}
