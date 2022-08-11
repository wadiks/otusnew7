package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.model.Books;

public interface BooksDao extends JpaRepository<Books, Long>, JpaSpecificationExecutor<Books> {

}
