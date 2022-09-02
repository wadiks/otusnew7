
package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.model.Books;

import java.util.List;

@RestController
public class RestBookController {

    private final BooksDao booksDao;

    @Autowired
    public RestBookController(BooksDao repository) {
        this.booksDao = repository;
    }

    @GetMapping("/api/books")
    public List<Books> listPage() {
        return booksDao.findAll();
    }
}
