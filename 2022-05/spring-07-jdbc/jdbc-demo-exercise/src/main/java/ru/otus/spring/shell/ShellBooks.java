package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Books;
import ru.otus.spring.service.ServiceBooks;

import java.util.Scanner;

@ShellComponent
public class ShellBooks implements SBooks {

    final ServiceBooks serviceBooks;


    public ShellBooks(ServiceBooks serviceBooks) {
        this.serviceBooks = serviceBooks;
    }
    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"c", "count"})
    public void count() {
        System.out.println("Количество книг = " + serviceBooks.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    public void getAll() {
        serviceBooks.bPrint(serviceBooks.getAll());
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
        serviceBooks.bPrint(serviceBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        serviceBooks.deleteById(number);
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    public void updateBook() {
        System.out.println("Какую книгу хотите изменить");
        serviceBooks.bPrint(serviceBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        int number = sc.nextInt();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
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

}
