package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceGenreImpl implements ServiceGenre {

    final GenreDao genreDao;

    public ServiceGenreImpl(GenreDao genreDao) {

        this.genreDao = genreDao;
    }

    public Long count() {

        return genreDao.count();
    }

    public Optional<Genre> getById(long id) {

        return genreDao.getById(id);
    }

    public List<Genre> getAll() {

        return genreDao.getAll();
    }

}
