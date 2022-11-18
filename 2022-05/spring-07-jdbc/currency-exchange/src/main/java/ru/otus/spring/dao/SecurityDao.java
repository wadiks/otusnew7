package ru.otus.spring.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.Security;

import java.util.List;

public interface SecurityDao extends CrudRepository<Security, Long> {

    @Override
    List<Security> findAll();

    @Query("select  s from Security s where s.userSecurity = :user")
    List<Security> findByUser(@Param("user") String user);
}
