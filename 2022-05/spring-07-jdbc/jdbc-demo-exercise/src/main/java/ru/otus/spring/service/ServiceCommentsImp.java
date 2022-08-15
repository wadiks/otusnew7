package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCommentsImp implements ServiceComments {

    final CommentDao commentDao;

    public ServiceCommentsImp(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Long count() {
        return commentDao.count();
    }

    public Optional<Comment> getById(long id) {
        return commentDao.findById(id);
    }

    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment insert(Comment comment) {
        commentDao.save(comment);
        return null;
    }

    @Override
    public Comment Update(int number, String comment) {
        final var fComment = getById(number).get();
        fComment.setKText(comment);
        commentDao.save(fComment);
        return null;
    }

    @Override
    public void deleteById(Comment comment) {
        commentDao.delete(comment);
    }

}
