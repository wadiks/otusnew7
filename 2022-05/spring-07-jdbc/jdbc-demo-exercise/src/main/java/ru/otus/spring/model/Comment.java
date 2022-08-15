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
    private long id;

    private String kText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Books book;

    public Comment(String kText, long book_id) {
        this.kText = kText;
    }

}
