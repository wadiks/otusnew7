package ru.otus.spring.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name ="genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  long id;

    @Column(name = "name")
    private  String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Books g_book;

    public Genre(long id, String name, Books books) {
        this.id = id;
        this.name = name;
        this.g_book = books;
    }
}
