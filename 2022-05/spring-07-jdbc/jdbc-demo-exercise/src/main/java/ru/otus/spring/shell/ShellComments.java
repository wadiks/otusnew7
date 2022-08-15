package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.ServiceBooks;
import ru.otus.spring.service.ServiceComments;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellComments implements SComments {

    final ServiceComments serviceComments;
    final ServiceBooks serviceBooks;
    final SBooks sBooks;

    public ShellComments(ServiceComments serviceComments, ServiceBooks serviceBooks, SBooks sBooks) {
        this.serviceComments = serviceComments;
        this.serviceBooks = serviceBooks;
        this.sBooks = sBooks;
    }

    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        int number = sc.nextInt();
        var commentById = serviceComments.getById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.get().getId(), commentById.get().getKtext()));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    public void delComment() {
        cComment(serviceComments.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        int gNumber = sc.nextInt();
        serviceComments.deleteById(serviceComments.getById(gNumber).get());
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    public void updateComment() {
        cComment(serviceComments.getAll());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        final String com = sc.nextLine();
        serviceComments.Update(gNumber, com);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    public void insertComment() {
        sBooks.bPrint(serviceBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        serviceComments.insert(new Comment(com, gNumber));
        System.out.println("Комментарий добавлен ");
    }

    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        cComment(serviceComments.getAll());
    }

    public void cComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format(" Название книги %s Номер комментария = %s Комментарий = %s", c.getBook_i().getName(), c.getId(), c.getKtext()));
        });
    }

}
