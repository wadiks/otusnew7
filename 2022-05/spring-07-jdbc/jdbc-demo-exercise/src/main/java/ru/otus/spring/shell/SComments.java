package ru.otus.spring.shell;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface SComments {
    public void getCommentById();

    public void delComment();

    public void updateComment();

    public void insertComment();

    public void cGetAll();

    void cComment(List<Comment> comment);
}
