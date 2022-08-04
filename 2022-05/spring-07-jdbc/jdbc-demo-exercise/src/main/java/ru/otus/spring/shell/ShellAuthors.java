package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.AuthorsDao;
import ru.otus.spring.model.Authors;

import java.util.List;
import java.util.Scanner;

@Service
@ShellComponent
public class ShellAuthors implements IAuthors {

    final  AuthorsDao authorsDao;

    public ShellAuthors(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    @ShellMethod(value = "Посмотреть всех аторов", key = {"authors", "aGetAll"})
    @Transactional(readOnly = true)
    public void getAutors() {
        System.out.println("Выведены все авторы книг = ");
        aPrint(authorsDao.getAll());
    }

    @ShellMethod(value = "Количество авторов", key = {"a", "aCount"})
    @Transactional(readOnly = true)
    public void getAutorsCount() {
        System.out.println("Количествол авторов = " + authorsDao.count());
    }

    @ShellMethod(value = "Найти автора по id", key = {"aId", "aGetId"})
    @Transactional(readOnly = true)
    public void getAutorsGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер автора:");
        int number = sc.nextInt();
        var author = authorsDao.getById(number);
        System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", author.get().getId(), author.get().getName(), author.get().getSurname()));
    }


    public void aPrint(List<Authors> authors) {
        authors.forEach(a -> {
            System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", a.getId(), a.getName(), a.getSurname()));
        });
    }

}
