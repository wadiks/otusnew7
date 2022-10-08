package ru.otus.spring.modelMongo;

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
public class BooksMongo {

    @Id
    private long id;

    private String name;

    private List<GenreMongo> genreMongos;

    private List<AuthorsMongo> authors;

}
