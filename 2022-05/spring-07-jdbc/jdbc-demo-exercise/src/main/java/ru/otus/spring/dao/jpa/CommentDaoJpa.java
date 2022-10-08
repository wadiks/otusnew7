package ru.otus.spring.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.modelSql.CommentM2;

public interface CommentDaoJpa extends CrudRepository<CommentM2, Long> {

}
