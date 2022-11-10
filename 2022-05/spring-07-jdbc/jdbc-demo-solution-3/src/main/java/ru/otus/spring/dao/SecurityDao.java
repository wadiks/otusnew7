package ru.otus.spring.dao;


import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Security;

import java.util.List;

public interface SecurityDao extends CrudRepository<Security, Long>  {

    @Override
    List<Security> findAll();
}
