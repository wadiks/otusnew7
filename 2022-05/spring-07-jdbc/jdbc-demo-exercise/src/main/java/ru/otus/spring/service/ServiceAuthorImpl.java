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

    public Long count() {
        return authorsDao.count();
    }

    public Optional<Authors> getById(long id) {
        return authorsDao.findById(id);
    }

    public List<Authors> getAll() {
        return authorsDao.findAll();
    }

    public void aPrint(List<Authors> authors) {
        authors.forEach(a -> {
            System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", a.getId(), a.getName(), a.getSurname()));
        });
    }
}
