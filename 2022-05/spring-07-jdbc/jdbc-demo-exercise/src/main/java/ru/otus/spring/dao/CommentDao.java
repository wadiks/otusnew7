package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentDao  extends MongoRepository<Comment, String> {

    List<Comment> findAll();
    @Query(value = "{'book_id.$id':?0}")
    List<Comment> findBooksById(Long value);

}
