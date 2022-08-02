package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@ShellComponent
public class ApplicationShellCommands {


    AuthorsDao authorsDao;
    BooksDao booksDao;
    CommentDao commentDao;
    GenreDao genreDao;

    @Autowired
    public ApplicationShellCommands(AuthorsDao authorsDao, BooksDao booksDao, CommentDao commentDao, GenreDao genreDao) {
        this.authorsDao = authorsDao;
        this.booksDao = booksDao;
        this.commentDao = commentDao;
        this.genreDao = genreDao;
    }

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
        System.out.println("Количество книг = " + booksDao.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    @Transactional(readOnly = true)
    public void getAll() {
      bPrint(booksDao.getAll());//,authorsDao.getAll(),genreDao.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    @Transactional(readOnly = true)
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = booksDao.getById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.get().getId(), book.get().getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    @Transactional()
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = booksDao.getById(number);
        booksDao.deleteById(book.get());
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    @Transactional()
    public void updateBook() {
        System.out.println("Какую книгу хотите изменить");
        bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
        gPrint(genreDao.getAll());
        System.out.println("Введите номер жанра:");
        int gNumber = sc.nextInt();
        aPrint(authorsDao.getAll());
        System.out.println("Введите номер автора:");
        int aNumber = sc.nextInt();
        var eBook = booksDao.getById(number).get();
        if (null != name) eBook.setName(name);
     //   if (0 != gNumber) eBook.setGenreId(gNumber);
       // if (0 != aNumber) eBook.setAuthorsId(aNumber);
        booksDao.save(eBook);
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    @Transactional()
    public void insertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        gPrint(genreDao.getAll());
        System.out.println("Введите номер жанра:");
        int gNumber = sc.nextInt();
        aPrint(authorsDao.getAll());
        System.out.println("Введите номер автора:");
        int aNumber = sc.nextInt();
        booksDao.save(new Books(name, gNumber, aNumber));
        System.out.println("Книга добавлена");
    }

    //  -------------------------------------------- авторы ------------------------------------------------------

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

    //-----------------------------------------------жанры -----------------------------------------------------------

    @ShellMethod(value = "Посмотреть все жанры", key = {"genres", "gGetAll"})
    @Transactional(readOnly = true)
    public void getGenre() {
        System.out.println("Все жанры книг = ");
        gPrint(genreDao.getAll());
    }

    @ShellMethod(value = "Количество жанров", key = {"g", "gCount"})
    @Transactional(readOnly = true)
    public void getGenreCount() {
        System.out.println("Количество жанров книг = " + genreDao.count());
    }

    @ShellMethod(value = "Найти жанр по id", key = {"gId", "gGetId"})
    @Transactional(readOnly = true)
    public void getGenreGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер жанра:");
        int number = sc.nextInt();
        var eGenre = genreDao.getById(number);
        System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", eGenre.get().getId(), eGenre.get().getName()));
    }

    //------------------------------------ комментарий -------------------------------------------------------------------
    @ShellMethod(value = "Найти коментарий по id", key = {"cId", "cGetId"})
    @Transactional(readOnly = true)
    public void getCommentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер комментария:");
        Long number = sc.nextLong();
        var commentById = commentDao.getById(number);
        System.out.println(String.format("Номер коментария = %s Комментарий = %s", commentById.get().getId(), commentById.get().getKText()));
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
        fComment.setKText(com);
        commentDao.save(fComment);
        System.out.println("Комментарий изменен");
    }

    @ShellMethod(value = "Добавить комментарий", key = {"insCom", "insertComment"})
    @Transactional()
    public void insertComment() {
        bPrint(booksDao.getAll());
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

    private void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s   ",
                    b.getId(), b.getName()));
            gPrint(b.getGenres());
            aPrint(b.getAuthors());
            System.out.println("------Следующая книга --------");
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

}


