package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.model.Books;
import ru.otus.spring.use.UseBooks;

import java.util.Scanner;

@ShellComponent
public class ShellBooks {

    final UseBooks useBooks;

    public ShellBooks(UseBooks useBooks) {
        this.useBooks = useBooks;
    }

    //--------------------------------------------книги ------------------------------------------------------------

    @ShellMethod(value = "Количество книг", key = {"cb", "count"})
    public void count() {
        System.out.println("Количество книг = " + useBooks.count());
    }

    @ShellMethod(value = "Вывод всех книг", key = {"books", "bGetAll"})
    public void getAll() {
        useBooks.bPrint(useBooks.getAll());
    }

    @ShellMethod(value = "Найти книгу по id", key = {"bId", "bGetId"})
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        var book = useBooks.getBookById(number);
        System.out.println(String.format("Номер книги = %s Наименование книги = %s", book.getId(), book.getName()));
    }

    @ShellMethod(value = "Удалить книгу", key = {"del", "delete"})
    public void delBook() {
        System.out.println("Какую книгу хотите удалить");
        useBooks.bPrint(useBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        useBooks.delBook(number);
        System.out.println("Книга удалена");
    }

    @ShellMethod(value = "Изменить название книги", key = {"rename"})
    public void updateBook() {
        System.out.println("Какую книгу хотите изменить");
        useBooks.bPrint(useBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        System.out.println("Введите название книги:");
        sc.nextLine();
        String name = sc.nextLine();
        useBooks.updateBook(number, name);
        System.out.println("Книга изменена");
    }

    @ShellMethod(value = "Добавить книгу", key = {"ins", "insert"})
    public void insertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название книги:");
        String name = sc.nextLine();
        useBooks.insertBook(new Books(name));
        System.out.println("Книга добавлена");
    }

    @ShellMethod(value = "Изменить автора у книги ", key = {"ea", "editAuthors"})
    public void getEditAuthors() {
        System.out.println("У какой книги хотете изменить автора");
        useBooks.bPrint(useBooks.getAll());
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер книги:");
        final var number = sc.nextLong();
        var book = useBooks.getBookById(number);
        System.out.println("Какого автора хотете измеить?");
        useBooks.aPrintNum(book.getAuthors());
        System.out.println("Введите номер автора:");
        final var aNumber = sc.nextInt();
        System.out.println(String.format("Вы выбрали изменить этого втора %s %s Введите полностью Имя и Фамилию через пробел", book.getAuthors().get(aNumber).getName(),
                book.getAuthors().get(aNumber).getSurname()));
        String fio = sc.nextLine();
        if (useBooks.editAuthors(book, aNumber, fio))
            System.out.println("Автор изменен");
        else
            System.out.println("Вы ввели не правильно Имя и фамилию поробуйте еще раз");
    }
}
