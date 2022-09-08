package ru.otus.spring.use;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.util.List;
@Service
public class UseBooksImp implements UseBooks {

    final BooksDao booksDao;

    public UseBooksImp(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    public Long count() {
        return booksDao.count();

    }

    public List<Books> getAll() {
        return booksDao.findAll();
    }

    public Books getBookById(long number) {
        return booksDao.findById(String.valueOf(number)).get();
    }

    public void delBook(long number) {
        booksDao.deleteById(String.valueOf(number));
    }

    public void updateBook(long number, String name) {
        var book = getBookById(number);
        book.setName(name);
        booksDao.save(book);
    }

    public void insertBook(Books book) {
        booksDao.save(book);
    }

    public boolean editAuthors(Books book, int number, String fio) {
        var words = fio.trim().split(" ");
        if (words.length > 1) {
            book.getAuthors().get(number).setName(words[0].trim());
            book.getAuthors().get(number).setSurname(words[1].trim());
            booksDao.save(book);
            return true;
        } else return false;
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

    public void aPrintNum(List<Authors> authors) {
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(String.format("Номер записи =%s  Имя и фамилия автора = %s %s", i, authors.get(i).getName(), authors.get(i).getSurname()));
        }
    }

    public void gPrint(List<Genre> genres) {
        genres.forEach(g -> {
            System.out.println(String.format("Наименование жанра = %s", g.getName()));
        });
    }

}
