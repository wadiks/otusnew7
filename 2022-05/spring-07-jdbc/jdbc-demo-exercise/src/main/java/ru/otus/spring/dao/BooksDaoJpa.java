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
    public Books insert(Books books) {
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
        return em.createQuery("select b from Books b join fetch b.comments ", Books.class)
                .getResultList();
    }



    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Books b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void update(Books books) {
        Query query = em.createQuery("update Books b " +
                "set b.authorsId = :authorsId, " +
                " b.genreId = :genreId, " +
                " b.name = :name " +
                "where b.id = :id");
        query.setParameter("authorsId", books.getAuthorsId());
        query.setParameter("genreId", books.getGenreId());
        query.setParameter("name", books.getName());
        query.executeUpdate();
    }
}
