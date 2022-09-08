package ru.otus.spring.use;

import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface UseBooks {

    public Long count();

    public List<Books> getAll();

    public Books getBookById(long number);

    public void delBook(long number);

    public void updateBook(long number, String name);

    public void insertBook(Books book);

    public boolean editAuthors (Books book, int number,String fio);

    public void bPrint(List<Books> books);

    public void aPrint(List<Authors> authors);

    public void aPrintNum(List<Authors> authors);

    public void gPrint(List<Genre> genres);

}
