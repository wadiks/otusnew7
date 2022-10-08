package ru.otus.spring.dao.mongo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.modelMongo.BooksMongo;

import java.util.List;

public interface BooksDaoMongo extends CrudRepository<BooksMongo, String> {

    List<BooksMongo> findAll();

}
