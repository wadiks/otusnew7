CREATE SEQUENCE BOOKS_SEQ_ID;

CREATE SEQUENCE COMMENT_SEQ_ID;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE
(
    ID      BIGINT PRIMARY KEY,
    NAME    VARCHAR(255),
    BOOK_ID BIGINT references BOOKS (id)
);

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS
(
    ID      BIGINT PRIMARY KEY,
    NAME    VARCHAR(255),
    SURNAME VARCHAR(255),
    BOOK_ID BIGINT references BOOKS (id)
);

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT
(
    ID      BIGINT PRIMARY KEY,
    KTEXT   VARCHAR(5000),
    BOOK_ID BIGINT references BOOKS (id) on delete cascade
);
