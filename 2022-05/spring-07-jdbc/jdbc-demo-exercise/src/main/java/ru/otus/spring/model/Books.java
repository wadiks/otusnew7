package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Books {

    @Id
    private Long id;

    private String name;

    private List<Genre> genres;

    private List<Authors> authors;

    public String getAuthor() {
        if (null != this.authors) {
            var rez = this.authors.stream().map(m -> m.getName() + " " + m.getSurname()).collect(Collectors.toSet());
            return String.join(",", rez);
        } else return "";
    }

    public String getGenre() {
        if (null != this.genres) {
            var rez =  this.genres.stream().map(m -> m.getName()).collect(Collectors.toSet());
            return String.join(",", rez);
        } else return "";
    }
}
