package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Books;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@ShellComponent
public class ShellBooks implements IBooks {

    final BooksDao booksDao;
    final IGenre genre;
    final GenreDao genreDao;
    final IAuthors authors;

    public ShellBooks(BooksDao booksDao, IGenre genre, GenreDao genreDao, IAuthors authors) {
        this.booksDao = booksDao;
        this.genre = genre;
        this.genreDao = genreDao;
        this.authors = authors;
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
        bPrint(booksDao.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = findById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.get().getId(), book.get().getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        bPrint(booksDao.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = findById(number);
        deleteByid(book.get());
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
        genre.gPrint(genreDao.getAll());
        var eBook = findById(number).get();
        if (null != name) eBook.setName(name);
        sav(eBook);
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    public void insertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        sav(new Books(name));
        System.out.println("Книга добавлена");
    }

    public void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s   ",
                    b.getId(), b.getName()));
            genre.gPrint(b.getGenres());
            authors.aPrint(b.getAuthors());
            System.out.println("------Следующая книга --------");
        });
    }


    @Transactional(readOnly = true)
    public Optional<Books> findById(int number){
        return booksDao.getById(number);
    }

    @Transactional()
    public void deleteByid(Books books){
        booksDao.deleteById(books);
    }

    @Transactional()
    public void sav(Books books){
        booksDao.save(books);
    }
}
