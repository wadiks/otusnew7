package ru.otus.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.model.Books;

public interface BooksDao extends CrudRepository<Books, Long> {

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    List<Books> findAll();

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    Optional<Books> findById(Long id);

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    Books save(Books entity);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(Books entity);
}
