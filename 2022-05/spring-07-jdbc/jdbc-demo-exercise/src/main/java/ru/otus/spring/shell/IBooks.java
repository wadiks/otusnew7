package ru.otus.spring.shell;

import ru.otus.spring.model.Books;
import java.util.List;

public interface IBooks {
    public void count();

    public void getAll();

    public void getBookById();

    public void delBook();

    public void updateBook();

    public void insertBook();

    public void bPrint(List<Books> books);
}
