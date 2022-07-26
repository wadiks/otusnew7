package ru.otus.spring.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authors_id")
    private Books a_book;

    public Authors(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;

    }

    public Authors(long id, String name,  String surname, Books books) {
        this.id = id;
        this.name = name;
        this.a_book = books;
        this.surname = surname;
    }

}
