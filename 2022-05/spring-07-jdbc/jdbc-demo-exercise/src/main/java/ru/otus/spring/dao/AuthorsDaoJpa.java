package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Authors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsDaoJpa implements AuthorsDao {

    @PersistenceContext
    private final EntityManager em;

    public AuthorsDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long count() {
        var count = em.createQuery("select  count(a) from Authors a ", Long.class).getSingleResult();
        return count == null ? 0L : count;
    }

    @Override
    public Optional<Authors> getById(long id) {
        return Optional.ofNullable(em.find(Authors.class, id));
    }

    @Override
    public List<Authors> getAll() {
        return em.createQuery("select  a from Authors a ", Authors.class)
                .getResultList();
    }
}
