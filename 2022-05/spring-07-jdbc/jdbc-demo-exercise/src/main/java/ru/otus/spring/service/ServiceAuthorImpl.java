package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorsDao;
import ru.otus.spring.model.Authors;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAuthorImpl implements ServiceAuthor {

    final AuthorsDao authorsDao;

    public ServiceAuthorImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    @Override
    public Long count() {
        return authorsDao.count();
    }

    @Override
    public Optional<Authors> getById(long id) {
        return authorsDao.findById(id);
    }

    @Override
    public List<Authors> getAll() {
        return authorsDao.findAll();
    }


}
