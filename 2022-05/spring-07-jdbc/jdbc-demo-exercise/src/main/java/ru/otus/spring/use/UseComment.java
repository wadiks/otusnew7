package ru.otus.spring.use;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface UseComment {

    public Long count();

    public Comment getCommentById(long number);

    public void delComment(long number);

    public void updateComment(long number, String textComment);

    public void insertComment(long bNumber,String com);

    public List<Comment>  findAll();

    public List<Comment> getAllCommentsFromBook(long number);

    public void printComment(List<Comment> comment);
    
}
