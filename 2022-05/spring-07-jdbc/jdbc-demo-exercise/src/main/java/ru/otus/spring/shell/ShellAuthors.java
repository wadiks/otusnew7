package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Authors;
import ru.otus.spring.service.ServiceAuthor;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellAuthors implements SAuthor {

    final ServiceAuthor serviceAuthor;

    public ShellAuthors(ServiceAuthor serviceAuthor) {
        this.serviceAuthor = serviceAuthor;
    }

    @ShellMethod(value = "Посмотреть всех аторов", key = {"authors", "aGetAll"})
    public void getAutors() {
        System.out.println("Выведены все авторы книг = ");
        aPrint(serviceAuthor.getAll());
    }

    @ShellMethod(value = "Количество авторов", key = {"a", "aCount"})
    public void getAutorsCount() {
        System.out.println("Количествол авторов = " + serviceAuthor.count());
    }

    @ShellMethod(value = "Найти автора по id", key = {"aId", "aGetId"})
    public void getAutorsGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер автора:");
        int number = sc.nextInt();
        var author = serviceAuthor.getById(number);
        System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", author.get().getId(), author.get().getName(), author.get().getSurname()));
    }

    public void aPrint(List<Authors> authors) {
        authors.forEach(a -> {
            System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", a.getId(), a.getName(), a.getSurname()));
        });
    }
}
