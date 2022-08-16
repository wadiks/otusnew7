package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    public CommentDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long count() {
        var rez = em.createQuery("select  count(c) from Comment c ", Long.class).getSingleResult();
        return rez;
    }

    @Override
    public Optional<Comment> getById(int id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("select  c from Comment c ", Comment.class)
                .getResultList();
    }

    @Override
    public List<Comment> getAll(int id) {
        return em.createQuery("select  c from Comment c where c.book_i = :bId ", Comment.class)
                .setParameter("bId",id)
                .getResultList();
    }

    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void deleteById(Comment coment) {
        em.remove(coment);
    }

}
