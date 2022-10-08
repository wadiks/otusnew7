package ru.otus.spring.modelMongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class CommentMongo {

    @Id
    private String id;

    private String ktext;

    @DBRef
    private BooksMongo book_id;

}
