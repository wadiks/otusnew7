package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellBooks implements SBooks {

    final BooksDao booksDao;


    public ShellBooks(BooksDao booksDao) {
        this.booksDao = booksDao;
    }
    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"c", "count"})
    public void count() {
        System.out.println("Количество книг = " + booksDao.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    public void getAll() {
        bPrint(booksDao.findAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        var book = booksDao.findById(String.valueOf(number));
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.get().getId(), book.get().getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        bPrint(booksDao.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        long number = sc.nextLong();
        booksDao.deleteById(String.valueOf(number));
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    public void updateBook() {
        System.out.println("Какую книгу хотите изменить");
        bPrint(booksDao.findAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
        var book = booksDao.findById(String.valueOf(number));
        book.get().setName(name);
        booksDao.save(book.get());
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    public void insertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        booksDao.save(new Books(name));
        System.out.println("Книга добавлена");
    }

    public void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s   ",
                    b.getId(), b.getName()));
            gPrint(b.getGenres());
            aPrint(b.getAuthors());
            System.out.println("------Следующая книга --------");
        });
    }

    public void aPrint(List<Authors> authors) {
        authors.forEach(a -> {
            System.out.println(String.format(" Назвавние автора = %s %s", a.getName(), a.getSurname()));
        });
    }

    public void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Наименование жанра = %s", g.getName()));
        });
    }




}
