package ru.otus.spring.shell;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface IComments {
    public void getCommentById();

    public void delComment();

    public void updateComment();

    public void insertComment();

    public void cCount();

    public void cGetAll();

    public void cComment(List<Comment> comment);
}
