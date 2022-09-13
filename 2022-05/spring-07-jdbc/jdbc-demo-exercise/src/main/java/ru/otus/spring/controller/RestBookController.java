
package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
    public Flux<Books> listPage() {
        return booksDao.findAll();
    }

    @GetMapping("/api/edit/{id}")
    public Mono<Books> byId(@PathVariable("id") String id) {
        return booksDao.findById(id);
    }

    @GetMapping("/api/bookDelete/{id}")
    public void delete (@PathVariable("id") String id) {
        booksDao.deleteById(id);
    }


    @PostMapping("/api/save")
    public Mono<Books> save(@RequestBody Mono<Books> dto) {
        return booksDao.save(dto);
    }
}
