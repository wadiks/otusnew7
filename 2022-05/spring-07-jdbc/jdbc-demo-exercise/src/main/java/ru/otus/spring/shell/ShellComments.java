package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellComments implements SComments {

    final CommentDao commentDao;
    final BooksDao booksDao;
    final SBooks sBooks;

    public ShellComments(CommentDao commentDao, BooksDao booksDao, SBooks sBooks) {
        this.commentDao = commentDao;
        this.booksDao = booksDao;
        this.sBooks = sBooks;
    }

    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        final var number = sc.nextLong();
        var commentById = commentDao.findById(String.valueOf(number));
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.get().getId(), commentById.get().getKtext()));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    public void delComment() {
        cComment(commentDao.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        long gNumber = sc.nextLong();
        commentDao.delete(commentDao.findById(String.valueOf(gNumber)).get());
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    public void updateComment() {
        cComment(commentDao.findAll());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        final var gNumber = sc.nextLong();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        final String com = sc.nextLine();
        var comment=  commentDao.findById(String.valueOf(gNumber)).get();
        comment.setKtext(com);
        commentDao.save(comment);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    public void insertComment() {
        sBooks.bPrint(booksDao.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        final var gNumber = sc.nextLong();
        final var books = booksDao.findById(String.valueOf(gNumber));
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        commentDao.save(new Comment(com, books.get()));
        System.out.println("Комментарий добавлен ");
    }

    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        sBooks.bPrint(booksDao.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги к которой хотите вывести все комментарий:");
        final var gNumber = sc.nextLong();
        final var comments =  commentDao.findBooksById(gNumber);
        cComment(comments);
    }

    @ShellMethod(value = "Количество коментариев", key = {"с", "сCount"})
    public void getCommentCount() {
        System.out.println("Количество коментариев к книгам = " + commentDao.count());
    }

    public void cComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format("  Номер комментария = %s Комментарий = %s",  c.getId(), c.getKtext()));
        });
    }

}
