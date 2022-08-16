package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentDao extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

}
