package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Scanner;

@Service
@ShellComponent
public class ShellComments implements IComments {

    final CommentDao commentDao;
    final IBooks iBooks;
    final BooksDao booksDao;

    public ShellComments(CommentDao commentDao, IBooks iBooks, BooksDao booksDao) {
        this.commentDao = commentDao;
        this.iBooks = iBooks;
        this.booksDao = booksDao;
    }

    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    @Transactional(readOnly = true)
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        Long number = sc.nextLong();
        var commentById = commentDao.getById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.get().getId(), commentById.get().getKtext()));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    @Transactional()
    public void delComment() {
        cComment(commentDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        int gNumber = sc.nextInt();
        commentDao.deleteById(commentDao.getById(gNumber).get());
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    @Transactional()
    public void updateComment() {
        cComment(commentDao.getAll());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        final String com = sc.nextLine();
        var fComment = commentDao.getById(gNumber).get();
        fComment.setKtext(com);
        commentDao.save(fComment);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    @Transactional()
    public void insertComment() {
        iBooks.bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        commentDao.save(new Comment(com, gNumber));
        System.out.println("Комментарий добавлен ");
    }

    @ShellMethod(value = "Количество коментариев.", key = {"cc", "countComment"})
    public void cCount() {
        System.out.println("Количество коментариев = " + commentDao.count());
    }

    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        cComment(commentDao.getAll());
    }


    public void cComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format("Номер комментария = %s Комментарий = %s", c.getId(), c.getKtext()));
        });
    }

}
