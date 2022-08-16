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

    @Override
    public Long count() {

        return commentDao.count();
    }

    @Override
    public Optional<Comment> getById(int id) {

        return commentDao.getById(id);
    }

    @Override
    public List<Comment> getAll() {

        return commentDao.getAll();
    }


    @Transactional()
    @Override
    public Comment insert(Comment comment) {
        commentDao.save(comment);
        return null;
    }

    @Transactional()
    @Override
    public Comment Update(int number, String comment) {
        final var fComment = getById(number).get();
        fComment.setKtext(comment);
        commentDao.save(fComment);
        return null;
    }

    @Transactional()
    @Override
    public void deleteById(Comment comment) {
        commentDao.deleteById(comment);

    }

}
