package ru.otus.spring.use;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.model.Comment;

import java.util.List;
@Service
public class UseCommentImp implements UseComment {

    final CommentDao commentDao;
    final BooksDao booksDao;

    public UseCommentImp(CommentDao commentDao, BooksDao booksDao) {
        this.commentDao = commentDao;
        this.booksDao = booksDao;
    }

    public Long count() {
        return commentDao.count();
    }

    public Comment getCommentById(long number) {
        return commentDao.findById(String.valueOf(number)).get();
    }

    public void delComment(long number) {
        commentDao.deleteById(String.valueOf(number));
    }

    public void updateComment(long number, String textComment) {
        var comment = commentDao.findById(String.valueOf(number)).get();
        comment.setKtext(textComment);
        commentDao.save(comment);
    }

    public void insertComment(long bNumber, String com) {
        final var books = booksDao.findById(String.valueOf(bNumber));
        commentDao.save(new Comment(com, books.get()));
    }

    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    public List<Comment> getAllCommentsFromBook(long number) {
        return commentDao.findBooksById(number);
    }

    public void printComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format("  Номер комментария = %s Комментарий = %s", c.getId(), c.getKtext()));
        });
    }
}
