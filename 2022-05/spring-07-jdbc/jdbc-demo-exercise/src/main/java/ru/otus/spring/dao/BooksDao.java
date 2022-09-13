package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Books;

public interface BooksDao extends ReactiveMongoRepository<Books, String> {

    Flux<Books> findAll();

    Mono<Books> save(Mono<Books> books);


}
