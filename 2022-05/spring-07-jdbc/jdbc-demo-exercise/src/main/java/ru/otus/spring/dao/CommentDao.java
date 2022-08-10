package ru.otus.spring.dao;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Long count();

    Optional<Comment> getById(int id);

    List<Comment> getAll();

    Comment save(Comment books);

    void deleteById(Comment comment);


}
