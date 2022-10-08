package ru.otus.spring.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.modelMongo.CommentMongo;

import java.util.List;

public interface CommentDaoMongo extends MongoRepository<CommentMongo, Long> {

    List<CommentMongo> findAll();

    @Query(value = "{'book_id.$id':?0}")
    List<CommentMongo> findBooksById(Long value);

}
