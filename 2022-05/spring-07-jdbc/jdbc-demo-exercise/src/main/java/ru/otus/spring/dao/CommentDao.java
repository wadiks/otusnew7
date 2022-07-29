package ru.otus.spring.dao;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentDao {

    Long count();

    Comment getById(long id);

    List<Comment> getAll();

    Comment insert(Comment books);

    void deleteById(long id);

    void updateNameById(long id, String kText);

}
