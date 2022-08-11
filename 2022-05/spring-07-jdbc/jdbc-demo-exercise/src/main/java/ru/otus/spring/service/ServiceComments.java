package ru.otus.spring.service;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ServiceComments {

    Long count();

    Optional<Comment> getById(long id);

    List<Comment> getAll();

    Comment insert(Comment comment);

    Comment Update( int number, String comment);

    void deleteById(Comment comment);

    void cComment(List<Comment> comment);
}
