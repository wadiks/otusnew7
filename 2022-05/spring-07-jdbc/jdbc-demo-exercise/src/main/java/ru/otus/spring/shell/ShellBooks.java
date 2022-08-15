package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Books;
import ru.otus.spring.service.ServiceBooks;
import ru.otus.spring.service.ServiceGenre;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellBooks implements SBooks {

    final ServiceBooks serviceBooks;
    final SAuthor sAuthor;
    final SGenre sGenre;
    final ServiceGenre serviceGenre;


    public ShellBooks(ServiceBooks serviceBooks, SAuthor sAuthor, SGenre sGenre, ServiceGenre serviceGenre) {
        this.serviceBooks = serviceBooks;
        this.sAuthor = sAuthor;
        this.sGenre = sGenre;
        this.serviceGenre = serviceGenre;
    }
    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"c", "count"})
    public void count() {
        System.out.println("Количество книг = " + serviceBooks.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    public void getAll() {
        bPrint(serviceBooks.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        var book = serviceBooks.getById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.get().getId(), book.get().getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        bPrint(serviceBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        long number = sc.nextLong();
        serviceBooks.deleteById(number);
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    public void updateBook() {
        System.out.println("Какую книгу хотите изменить");
        bPrint(serviceBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
        sGenre.gPrint(serviceGenre.getAll());
        serviceBooks.Update(number, name);
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    public void insertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        serviceBooks.save(new Books(name));
        System.out.println("Книга добавлена");
    }

    public void bPrint(List<Books> books) {
        books.forEach(b -> {
            System.out.println(String.format("Номер книги = %s Наименование книги = %s   ",
                    b.getId(), b.getName()));
            sGenre.gPrint(b.getGenres());
            sAuthor.aPrint(b.getAuthors());
            System.out.println("------Следующая книга --------");
        });
    }

}
