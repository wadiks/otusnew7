package ru.otus.spring.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.modelSql.GenreM2;

public interface GenreDaoJpa extends CrudRepository<GenreM2, Long> {

}
