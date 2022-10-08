package ru.otus.spring.modelSql;

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
public class BooksM2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSeq")
    @SequenceGenerator(name = "menuSeq",  allocationSize = 1, sequenceName = "BOOKS_SEQ_ID")
    private long id;

    @Column(name = "name")
    private String name;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = GenreM2.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_genre", joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreM2> genreM2s;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = AuthorsM2.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<AuthorsM2> authors;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = CommentM2.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<CommentM2> commentM2s;

}