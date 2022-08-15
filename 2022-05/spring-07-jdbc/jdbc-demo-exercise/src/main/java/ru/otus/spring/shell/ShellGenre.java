package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.ServiceGenre;
import ru.otus.spring.service.ServiceGenreImpl;

import java.util.List;
import java.util.Scanner;


@ShellComponent
public class ShellGenre implements SGenre {

    final ServiceGenre serviceGenre;

    public ShellGenre(ServiceGenreImpl serviceGenre) {
        this.serviceGenre = serviceGenre;
    }

    @ShellMethod(value = "Посмотреть все жанры", key = {"genres", "gGetAll"})

    public void getGenre() {
        System.out.println("Все жанры книг = ");
        gPrint(serviceGenre.getAll());
    }

    @ShellMethod(value = "Количество жанров", key = {"g", "gCount"})
    public void getGenreCount() {
        System.out.println("Количество жанров книг = " + serviceGenre.count());
    }

    @ShellMethod(value = "Найти жанр по id", key = {"gId", "gGetId"})

    public void getGenreGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер жанра:");
        int number = sc.nextInt();
        final var eGenre = serviceGenre.getById(number);
        System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", eGenre.get().getId(), eGenre.get().getName()));
    }
    public void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", g.getId(), g.getName()));
        });
    }

}
