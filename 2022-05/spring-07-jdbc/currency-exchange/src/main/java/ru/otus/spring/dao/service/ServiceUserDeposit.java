package ru.otus.spring.dao.service;

import ru.otus.spring.model.UserDeposit;

import java.util.List;
import java.util.Optional;

public interface ServiceUserDeposit {

    Optional<UserDeposit> getById(long id);

    List<UserDeposit> getAll();

    void save(UserDeposit entity);

    void insert(UserDeposit entity);

}
