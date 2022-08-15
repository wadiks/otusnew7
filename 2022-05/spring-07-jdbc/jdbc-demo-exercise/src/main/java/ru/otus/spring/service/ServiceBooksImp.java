package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Books;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBooksImp implements ServiceBooks {

    final BooksDao booksDao;
    final GenreDao genreDao;
    final ServiceGenre serviceGenre;
    final ServiceAuthor serviceAuthor;

    public ServiceBooksImp(BooksDao booksDao, GenreDao genreDao, ServiceGenre serviceGenre, ServiceAuthor serviceAuthor) {
        this.booksDao = booksDao;
        this.genreDao = genreDao;
        this.serviceGenre = serviceGenre;
        this.serviceAuthor = serviceAuthor;
    }

    @Override
    public Long count() {
        return booksDao.count();
    }

    @Override
    public Books save(Books books) {
        return booksDao.save(books);
    }

    public void Update(int number, String name) {
        var eBook = getById(number).get();
        if (null != name) eBook.setName(name);
        save(eBook);
    }

    @Override
    public Optional<Books> getById(long id) {
        return booksDao.findById(id);
    }

    @Override
    public List<Books> getAll() {
        return booksDao.findAll();
    }

    @Override
    public void deleteById(long number) {
        var book = booksDao.findById(number);
        booksDao.delete(book.get());
    }

}
