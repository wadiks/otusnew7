package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Scanner;

@Service
@ShellComponent
public class ShellGenre implements IGenre {

    final GenreDao genreDao;

    public ShellGenre(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Посмотреть все жанры", key = {"genres", "gGetAll"})
    @Transactional(readOnly = true)
    public void getGenre() {
        System.out.println("Все жанры книг = ");
        gPrint(genreDao.getAll());
    }

    @ShellMethod(value = "Количество жанров", key = {"g", "gCount"})
    @Transactional(readOnly = true)
    public void getGenreCount() {
        System.out.println("Количество жанров книг = " + genreDao.count());
    }

    @ShellMethod(value = "Найти жанр по id", key = {"gId", "gGetId"})
    @Transactional(readOnly = true)
    public void getGenreGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер жанра:");
        int number = sc.nextInt();
        var eGenre = genreDao.getById(number);
        System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", eGenre.get().getId(), eGenre.get().getName()));
    }

    public void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", g.getId(), g.getName()));
        });
    }


}
