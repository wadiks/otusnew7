package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Authors.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Authors> authors;


    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    public Books(String name, Integer genreId, Integer authorsId) {
        this.name = name;
    }
}