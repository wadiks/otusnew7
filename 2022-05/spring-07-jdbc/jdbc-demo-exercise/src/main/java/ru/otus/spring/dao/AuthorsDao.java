package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Authors;

import java.util.List;

public interface AuthorsDao extends CrudRepository<Authors, Long> {

    List<Authors> findAll();

}
