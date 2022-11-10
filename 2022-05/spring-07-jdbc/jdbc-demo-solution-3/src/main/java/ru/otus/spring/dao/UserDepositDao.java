package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.UserDeposit;

import java.util.List;

public interface UserDepositDao extends CrudRepository<UserDeposit, Long> {
    @Override
    List<UserDeposit> findAll();
}
