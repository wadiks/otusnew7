package ru.otus.spring.shell;

import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.AuthorsDao;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.sourse.ApplicationContextHolder;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ApplicationShellCommands {

    @ShellMethod(value = "Help command", key = {"?"})
    public void help() {
        System.out.println("Список команд которые можно сделать.");
        System.out.println("Посмотреть весь каталог книг. Команда - books , bGetAll");
        System.out.println("Количество книг. Команда - c, count");
        System.out.println("Добавить книгу. Команда - ins, insert");
        System.out.println("Удалить книгу. Команда - del, delete");
        System.out.println("Найти книгу по id. Команда - bId, bGetId");
        System.out.println("Изменить название книги. Команда - rename");
        System.out.println("Количество авторов. Команда - a, aCount");
        System.out.println("Посмотреть всех аторов. Команда - authors , aGetAll");
        System.out.println("Найти автора по id. Команда - aId, aGetId");
        System.out.println("Количество жанров. Команда - g, gCount");
        System.out.println("Посмотреть все жанры. Команда - genres, gGetAll");
        System.out.println("Найти жанр по id. Команда - gId, gGetId");
        System.out.println("Посмотреть все коментарии к книге. Команда - comment , cGetAll");
        System.out.println("Количество коментариев. Команда - cc, countComment");
        System.out.println("Добавить коментарий. Команда - insCom, insertComment");
        System.out.println("Удалить коментарий. Команда - delCom, deleteComment");
        System.out.println("Найти коментарий по id. Команда - cId, cGetId");
        System.out.println("Изменить коментарий книги. Команда - cRename");
    }

    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"c", "count"})
    @Transactional(readOnly = true)
    public void count() {
        var books = context().getBean(BooksDao.class);
        System.out.println("Количество книг = " + books.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    @Transactional(readOnly = true)
    public void getAll() {
        var books = context().getBean(BooksDao.class);
        bPrint(books.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    @Transactional(readOnly = true)
    public void getBookById() {
        var books = context().getBean(BooksDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = books.getById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.get().getId(), book.get().getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    @Transactional()
    public void delBook() {
        var books = context().getBean(BooksDao.class);
        System.out.println("Какую книгу хотите удалить");
        bPrint(books.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        books.deleteById(number);
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    @Transactional()
    public void updateBook() {
        var books = context().getBean(BooksDao.class);
        var authors = context().getBean(AuthorsDao.class);
        var genre = context().getBean(GenreDao.class);
        System.out.println("Какую книгу хотите изменить");
        bPrint(books.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
        gPrint(genre.getAll());
        System.out.println("Введите номер жанра:");
        int gNumber = sc.nextInt();
        aPrint(authors.getAll());
        System.out.println("Введите номер автора:");
        int aNumber = sc.nextInt();
        books.update(new Books(number, name, gNumber, aNumber));
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    @Transactional()
    public void insertBook() {
        var books = context().getBean(BooksDao.class);
        var authors = context().getBean(AuthorsDao.class);
        var genre = context().getBean(GenreDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        gPrint(genre.getAll());
        System.out.println("Введите номер жанра:");
        int gNumber = sc.nextInt();
        aPrint(authors.getAll());
        System.out.println("Введите номер автора:");
        int aNumber = sc.nextInt();
        books.insert(new Books(name, gNumber, aNumber));
        System.out.println("Книга добавлена");
    }

    //  -------------------------------------------- авторы ------------------------------------------------------

    @ShellMethod(value = "Посмотреть всех аторов", key = {"authors", "aGetAll"})
    @Transactional(readOnly = true)
    public void getAutors() {
        var autors = context().getBean(AuthorsDao.class);
        System.out.println("Выведены все авторы книг = ");
        aPrint(autors.getAll());
    }

    @ShellMethod(value = "Количество авторов", key = {"a", "aCount"})
    @Transactional(readOnly = true)
    public void getAutorsCount() {
        var autors = context().getBean(AuthorsDao.class);
        System.out.println("Количествол авторов = " + autors.count());
    }

    @ShellMethod(value = "Найти автора по id", key = {"aId", "aGetId"})
    @Transactional(readOnly = true)
    public void getAutorsGetId() {
        var autors = context().getBean(AuthorsDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер автора:");
        int number = sc.nextInt();
        var author = autors.getById(number);
        System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", author.get().getId(), author.get().getName(), author.get().getSurname()));
    }

    //-----------------------------------------------жанры -----------------------------------------------------------

    @ShellMethod(value = "Посмотреть все жанры", key = {"genres", "gGetAll"})
    @Transactional(readOnly = true)
    public void getGenre() {
        var genre = context().getBean(GenreDao.class);
        System.out.println("Все жанры книг = ");
        gPrint(genre.getAll());
    }

    @ShellMethod(value = "Количество жанров", key = {"g", "gCount"})
    @Transactional(readOnly = true)
    public void getGenreCount() {
        var genre = context().getBean(GenreDao.class);
        System.out.println("Количество жанров книг = " + genre.count());
    }

    @ShellMethod(value = "Найти жанр по id", key = {"gId", "gGetId"})
    @Transactional(readOnly = true)
    public void getGenreGetId() {
        var genre = context().getBean(GenreDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер жанра:");
        int number = sc.nextInt();
        var eGenre = genre.getById(number);
        System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", eGenre.get().getId(), eGenre.get().getName()));
    }

    //------------------------------------ комментарий -------------------------------------------------------------------
    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    @Transactional(readOnly = true)
    public void getCommentById() {
        var comment = context().getBean(CommentDao.class);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        Long number = sc.nextLong();
        var commentById = comment.getById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.getId(), commentById.getKText()));
    }

    @ShellMethod(value = "Удалить коментарий", key = {"delCom", "deleteComment"})
    @Transactional()
    public void delComment() {
        var comment = context().getBean(CommentDao.class);
        cComment(comment.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите удалить:");
        int gNumber = sc.nextInt();
        comment.deleteById(gNumber);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Изменить коментарий", key = {"cRename"})
    @Transactional()
    public void updateComment() {
        var comment = context().getBean(CommentDao.class);
        cComment(comment.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария которой хотите изменить:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        comment.updateNameById(gNumber, com);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    @Transactional()
    public void insertComment() {
        var comment = context().getBean(CommentDao.class);
        var books = context().getBean(BooksDao.class);
        bPrint(books.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги в которую хотите добавить комментарий:");
        int gNumber = sc.nextInt();
        System.out.println("Введите комментарий:");
        sc.nextLine();
        String com = sc.nextLine();
        comment.insert(new Comment(com, gNumber));
        System.out.println("Комментарий добавлен ");
    }

    public ApplicationContext context() {
        var ctx = ApplicationContextHolder.getApplicationContext();
        if (null == ctx) System.out.println("Не загрузился контекст");
        return ctx;
    }

    private void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s  Номер в таблице жанров = %s  Номер в таблице авторов = %s  ",
                    b.getId(), b.getName(), b.getGenreId(), b.getAuthorsId()));
        });
    }

    private void aPrint(List<Authors> authors) {
        authors.forEach(a -> {
            System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", a.getId(), a.getName(), a.getSurname()));
        });
    }

    private void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", g.getId(), g.getName()));
        });
    }

    private void cComment(List<Comment> comment) {
        comment.forEach(c -> {
            System.out.println(String.format("Номер комментария = %s Комментарий = %s", c.getId(), c.getKText()));
        });
    }

    @ShellMethod(value = "Количество коментариев.", key = {"cc", "countComment"})
    public void cCount() {
        var comment = context().getBean(CommentDao.class);
        System.out.println("Количество коментариев = " + comment.count());
    }

    @ShellMethod(value = "Посмотреть все коментарии к книге.", key = {"comment", "cGetAll"})
    public void cGetAll() {
        var comment = context().getBean(CommentDao.class);
        cComment(comment.getAll());
    }
}


