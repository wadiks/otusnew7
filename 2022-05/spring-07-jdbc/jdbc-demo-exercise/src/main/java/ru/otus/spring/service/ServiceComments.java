package ru.otus.spring.service;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ServiceComments {

    Long count();

    Optional<Comment> getById(int id);

    List<Comment> getAll();

    List<Comment> getAll(int id);

    Comment insert(Comment comment);

    Comment Update( int number, String comment);

    void deleteById(Comment comment);

}
