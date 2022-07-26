package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.model.Books;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@DisplayName("Запуск теста Dao")
@JdbcTest
@Import({ BooksDaoJdbc.class})
class BooksDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 8;
    private static final int EXISTING_Books_ID = 1;
    private static final String EXISTING_Books_NAME = "Мидлмарч";


    @Autowired
    private BooksDaoJdbc booksJdbc;

    @BeforeTransaction
    void beforeTransaction(){
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction(){
        System.out.println("afterTransaction");
    }

    @DisplayName("Количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = booksJdbc.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавление книг в БД")
    @Test
    void shouldInsertBooks() {
        Books expectedBooks = new Books("Война и мир", 5,9);
        booksJdbc.insert(expectedBooks);
        Books actualBooks = booksJdbc.getById(expectedBooks.getId());
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @DisplayName("возвращение книги по его id")
    @Test
    void shouldReturnExpectedBooksById() {
        Books expectedBooks = new Books(EXISTING_Books_ID, EXISTING_Books_NAME,2,1);
        Books actualBooks = booksJdbc.getById(expectedBooks.getId());
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBooksById() {
        assertThatCode(() -> booksJdbc.getById(EXISTING_Books_ID))
                .doesNotThrowAnyException();

        booksJdbc.deleteById(EXISTING_Books_ID);

        assertThatThrownBy(() -> booksJdbc.getById(EXISTING_Books_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать список книг")
    @Test
    void shouldReturnExpectedBookssList() {
        List<Books> actualBooksList = booksJdbc.getAll();
        assertNotNull("Обьект возвращает null проверьте данные",actualBooksList);
    }

}
