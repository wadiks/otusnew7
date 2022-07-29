package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSeq")
    @SequenceGenerator(name = "menuSeq",  allocationSize = 1, sequenceName = "BOOKS_SEQ_ID")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "authors_id")
    private Integer authorsId;

    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    public Books(String name, Integer genreId, Integer authorsId) {
        this.name = name;
        this.genreId = genreId;
        this.authorsId = authorsId;
    }
    public Books(long id, String name, Integer genreId, Integer authorsId) {
        this.id = id;
        this.name = name;
        this.genreId = genreId;
        this.authorsId = authorsId;
    }
}
