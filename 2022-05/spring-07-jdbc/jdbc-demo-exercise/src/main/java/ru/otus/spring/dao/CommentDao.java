package ru.otus.spring.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.model.Comment;

public interface CommentDao extends MongoRepository<Comment, String> {
    List<Comment> findAll();

    @Query("{'book_id.$id':?0}")
    List<Comment> findBooksById(Long value);
}
