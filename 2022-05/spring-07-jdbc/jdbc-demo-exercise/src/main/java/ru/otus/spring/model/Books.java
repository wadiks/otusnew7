package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Books {

    @Id
    private long id;

    private String name;

    private List<Genre> genres;

    private List<Authors> authors;

    private List<Comment> comments;

    public Books(String name) {
        this.name = name;
    }
}
