package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksDaoJpa implements BooksDao {

    @PersistenceContext
    private final EntityManager em;

    public BooksDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long count() {
       var count =  em.createQuery("select  count(b) from Books b ", Long.class).getSingleResult();
       return count == null ? 0L : count;
    }

    @Override
    public Books save(Books books) {
        if (books.getId() <= 0) {
            em.persist(books);
            return books;
        } else {
            return em.merge(books);
        }
    }

    @Override
    public Optional<Books> getById(long id) {
        return Optional.ofNullable(em.find(Books.class, id));
    }

    @Override
    public List<Books> getAll() {
        return em.createQuery("select b from Books b left join fetch b.comments ", Books.class)
                .getResultList();
    }

    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")

    @Override
    public void deleteById(Books book) {
         em.remove(book);
    }

}
