package ru.otus.spring.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.modelSql.BooksM2;

public interface BooksDaoJpa extends CrudRepository<BooksM2, Long> {
}
