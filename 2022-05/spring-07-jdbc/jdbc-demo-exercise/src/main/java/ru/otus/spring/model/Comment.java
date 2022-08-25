package ru.otus.spring.model;

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
public class Comment {

    @Id
    private String id;

    private String ktext;
    @DBRef
    private Books book_id;

    public Comment(String kText, Books book_id) {
        this.ktext = kText;
        this.book_id = book_id;
    }

}
