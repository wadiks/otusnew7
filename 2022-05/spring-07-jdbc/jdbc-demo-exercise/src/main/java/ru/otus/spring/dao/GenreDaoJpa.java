package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    public GenreDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long count() {
        var count = em.createQuery("select  count(g) from Genre g ", Long.class).getSingleResult();
        return count == null ? 0L : count;
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select  g from Genre g ", Genre.class)
                .getResultList();
    }
}
