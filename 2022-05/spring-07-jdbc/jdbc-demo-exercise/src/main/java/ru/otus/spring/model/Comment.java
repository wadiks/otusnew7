package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "comment")
public class Comment {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSeq")
    @SequenceGenerator(name = "menuSeq", allocationSize = 1, sequenceName = "COMMENT_SEQ_ID")
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "KTEXT")
    private String kText;

    @Column(name = "BOOK_ID")
    private long bookId;

    public Comment(String kText, long bookId) {
        this.kText = kText;
        this.bookId = bookId;
    }

}
