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

        return genreDao.findById(id);
    }

    public List<Genre> getAll() {

        return genreDao.findAll();
    }

    public void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", g.getId(), g.getName()));
        });
    }
}
