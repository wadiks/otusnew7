package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {

    @Id
    private long id;

    private String kText;

    private Books book_id;

    public Comment(String kText, long book_id) {
        this.kText = kText;
    }

}
