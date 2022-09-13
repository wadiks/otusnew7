package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Books;

import java.util.List;

public interface BooksDao extends ReactiveMongoRepository<Books, String> {

    Flux<Books> findAll();

    Mono<Books> save (Mono<Books> books);


}
