package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;
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
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        int number = sc.nextInt();
        var commentById = findById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.get().getId(), commentById.get().getKtext()));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    public void delComment() {
        cComment(commentDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        int gNumber = sc.nextInt();
        deleteByid(findById(gNumber).get());
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    public void updateComment() {
        cComment(commentDao.getAll());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        final String com = sc.nextLine();
        var fComment = findById(gNumber).get();
        fComment.setKtext(com);
        sav(fComment);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    public void insertComment() {
        iBooks.bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        sav(new Comment(com, gNumber));
        System.out.println("Комментарий добавлен ");
    }

    @ShellMethod(value = "Количество коментариев.", key = {"cc", "countComment"})
    public void cCount() {
        System.out.println("Количество коментариев = " + commentDao.count());
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        cComment(commentDao.getAll());
    }


    public void cComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format(" Название книги %s Номер комментария = %s Комментарий = %s", c.getBook_i().getName(), c.getId(), c.getKtext()));
        });
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(int number){
        return commentDao.getById(number);
    }

    @Transactional()
    public void deleteByid(Comment com){
        commentDao.deleteById(com);
    }

    @Transactional()
    public void sav(Comment com){
        commentDao.save(com);
    }

}
