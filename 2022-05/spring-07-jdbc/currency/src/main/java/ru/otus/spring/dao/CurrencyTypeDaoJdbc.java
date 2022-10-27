package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.bd.CurrencyType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CurrencyTypeDaoJdbc implements CurrencyTypeDao {

    @PersistenceContext
    private final EntityManager em;

    public CurrencyTypeDaoJdbc(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<CurrencyType> getAll() {
        return em.createQuery("select  ct from CurrencyType ct ", CurrencyType.class)
                .getResultList();
    }


}
/*
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

    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);


 */