package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorsDao;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private BooksDao booksDao;
    private AuthorsDao authorsDao;
    private GenreDao genreDao;

    @Autowired
    public ApplicationShellCommands(BooksDao booksDao, AuthorsDao authorsDao, GenreDao genreDao) {
        this.booksDao = booksDao;
        this.authorsDao = authorsDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Help command", key = {"?"})
    public void help() {
        System.out.println("Список команд которые можно сделать.");
        System.out.println("Посмотреть весь каталог книг. Команда - books , bGetAll");
        System.out.println("Количество книг. Команда - c, count");
        System.out.println("Добавить книгу. Команда - ins, insert");
        System.out.println("Удалить книгу книгу. Команда - del, delete");
        System.out.println("Найти книгу по id. Команда - bId, bGetId");
        System.out.println("Изменить название книги. Команда - rename");
        System.out.println("Количество авторов. Команда - a, aCount");
        System.out.println("Посмотреть всех аторов. Команда - authors , aGetAll");
        System.out.println("Найти автора по id. Команда - aId, aGetId");
        System.out.println("Количество жанров. Команда - g, gCount");
        System.out.println("Посмотреть все жанры. Команда - genres, gGetAll");
        System.out.println("Найти жанр по id. Команда - gId, gGetId");
    }

    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"c", "count"})
    public void count() {
        System.out.println("Количество книг = " + booksDao.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    public void getAll() {
        bPrint(booksDao.getAll(), authorsDao.getAll(), genreDao.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = booksDao.getById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.getId(), book.getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        booksDao.deleteById(number);
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
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
        booksDao.update(new Books(number, name, gNumber, aNumber));
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
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
        booksDao.insert(new Books(name, gNumber, aNumber));
        System.out.println("Книга добавлена");
    }

    //  -------------------------------------------- авторы ------------------------------------------------------

    @ShellMethod(value = "Посмотреть всех аторов", key = {"authors", "aGetAll"})
    public void getAutors() {
        System.out.println("Выведены все авторы книг = ");
        aPrint(authorsDao.getAll());
    }

    @ShellMethod(value = "Количество авторов", key = {"a", "aCount"})
    public void getAutorsCount() {
        System.out.println("Количествол авторов = " + authorsDao.count());
    }

    @ShellMethod(value = "Найти автора по id", key = {"aId", "aGetId"})
    public void getAutorsGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер автора:");
        int number = sc.nextInt();
        var author = authorsDao.getById(number);
        System.out.println(String.format("Номер автора = %s Назвавние автора = %s %s", author.getId(), author.getName(), author.getSurname()));
    }

    //-----------------------------------------------жанры -----------------------------------------------------------

    @ShellMethod(value = "Посмотреть все жанры", key = {"genres", "gGetAll"})
    public void getGenre() {
        System.out.println("Все жанры книг = ");
        gPrint(genreDao.getAll());
    }

    @ShellMethod(value = "Количество жанров", key = {"g", "gCount"})
    public void getGenreCount() {
        System.out.println("Количество жанров книг = " + genreDao.count());
    }

    @ShellMethod(value = "Найти жанр по id", key = {"gId", "gGetId"})
    public void getGenreGetId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер жанра:");
        int number = sc.nextInt();
        var eGenre = genreDao.getById(number);
        System.out.println(String.format("Номер жанра = %s Наименование жанра = %s", eGenre.getId(), eGenre.getName()));
    }


    private void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s  Номер в таблице жанров = %s  Номер в таблице авторов = %s  ",
                    b.getId(), b.getName(), b.getGenreId(), b.getAuthorsId()));
        });
    }

    private void bPrint(List<Books> books, List<Authors> authors, List<Genre> genres) {
        books.forEach(b -> {
                    var a = authors.stream().filter(authors1 -> authors1.getId() == b.getAuthorsId()).collect(Collectors.toList());
                    var g = genres.stream().filter(authors1 -> authors1.getId() == b.getGenreId()).collect(Collectors.toList());
                    System.out.println(String.format("Номер книги = %s Наименование книги = %s  Жанр = %s  Автор = %s  ",
                            b.getId(), b.getName(), null != g.get(0) ? g.get(0).getName() : "",
                            null != a.get(0) ? a.get(0).getSurname() + " " + a.get(0).getName() : ""));
                }
        );
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
}


