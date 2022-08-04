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

    private String ktext;

    private long book_id;

    public Comment(String ktext, long book_id) {
        this.ktext = ktext;
        this.book_id = book_id;
    }

}
