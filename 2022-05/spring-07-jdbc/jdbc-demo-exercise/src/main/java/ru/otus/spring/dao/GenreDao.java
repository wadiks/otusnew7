package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.model.Genre;

public interface GenreDao extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

}
