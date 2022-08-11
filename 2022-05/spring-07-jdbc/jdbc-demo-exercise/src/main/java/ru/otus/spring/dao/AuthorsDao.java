package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.model.Authors;

public interface AuthorsDao extends JpaRepository<Authors, Long>, JpaSpecificationExecutor<Authors> {

}
