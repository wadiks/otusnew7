package ru.otus.spring.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Books {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "authors_id")
    private Integer authorsId;

    public Books(long id, String name, Integer genreId, Integer authorsId) {
        this.id = id;
        this.name = name;
        this.genreId = genreId;
        this.authorsId = authorsId;
    }

    public Books( String name, Integer genreId, Integer authorsId) {
        this.name = name;
        this.genreId = genreId;
        this.authorsId = authorsId;
    }

}
