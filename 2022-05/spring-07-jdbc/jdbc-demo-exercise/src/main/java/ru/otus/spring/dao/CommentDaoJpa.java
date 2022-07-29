package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    public Comment getById(long id) {
        return em.createQuery("select  c from Comment c where c.id = :ID ", Comment.class)
                .setParameter("ID", id)
                .getSingleResult();
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("select  c from Comment c ", Comment.class)
                .getResultList();
    }

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }


    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateNameById(long id, String kText) {
        Query query = em.createQuery("update Comment c " +
                "set c.kText = :kText " +
                "where c.id = :id");
        query.setParameter("kText", kText);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
