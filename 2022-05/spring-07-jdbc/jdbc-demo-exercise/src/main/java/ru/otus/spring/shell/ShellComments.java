package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.ServiceBooks;
import ru.otus.spring.service.ServiceComments;

import java.util.Scanner;

@ShellComponent
public class ShellComments implements SComments {

    final ServiceComments serviceComments;
    final ServiceBooks serviceBooks;

    public ShellComments(ServiceComments serviceComments, ServiceBooks serviceBooks) {
        this.serviceComments = serviceComments;
        this.serviceBooks = serviceBooks;
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
        serviceComments.cComment(serviceComments.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        long gNumber = sc.nextLong();
        serviceComments.deleteById(serviceComments.getById(gNumber).get());
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    public void updateComment() {
        serviceComments.cComment(serviceComments.getAll());
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
        serviceBooks.bPrint(serviceBooks.getAll());
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
        serviceComments.cComment(serviceComments.getAll());
    }

    @ShellMethod(value = "Количество коментариев", key = {"с", "сCount"})
    public void getCommentCount() {
        System.out.println("Количество коментариев к книгам = " + serviceComments.count());
    }

}
