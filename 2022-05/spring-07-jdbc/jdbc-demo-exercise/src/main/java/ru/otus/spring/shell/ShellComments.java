package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.use.UseBooks;
import ru.otus.spring.use.UseComment;

import java.util.Scanner;

@ShellComponent
public class ShellComments {

    final UseComment useComment;
    final UseBooks useBooks;

    public ShellComments(UseComment useComment, UseBooks useBooks) {
        this.useComment = useComment;
        this.useBooks = useBooks;
    }

    @ShellMethod(value = "Количество коментариев", key = {"с", "сCount"})
    public void getCommentCount() {
        System.out.println(String.format("Количество коментариев к книгам = %s", useComment.count()));
    }

    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        final var number = sc.nextLong();
        final var commentById = useComment.getCommentById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.getId(), commentById.getKtext()));
    }

    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        useBooks.bPrint(useBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги к которой хотите вывести все комментарий:");
        final var gNumber = sc.nextLong();
        useComment.printComment(useComment.getAllCommentsFromBook(gNumber));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    public void delComment() {
        useComment.printComment(useComment.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        final var gNumber = sc.nextLong();
        useComment.delComment(gNumber);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    public void updateComment() {
        useComment.printComment(useComment.findAll());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        final var gNumber = sc.nextLong();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        final String com = sc.nextLine();
        useComment.updateComment(gNumber,com);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    public void insertComment() {
        useBooks.bPrint(useBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        final var gNumber = sc.nextLong();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        useComment.insertComment(gNumber,com);
        System.out.println("Комментарий добавлен ");
    }
}
