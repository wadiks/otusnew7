package ru.otus.spring.dao.service;

import ru.otus.spring.model.Security;

import java.util.List;

public interface ServiceSecurity {

    List<Security> findAll();
    Security findByUser(String user);
}
