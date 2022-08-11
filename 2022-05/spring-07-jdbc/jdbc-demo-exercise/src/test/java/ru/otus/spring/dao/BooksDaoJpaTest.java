package ru.otus.spring.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Books;
import ru.otus.spring.service.ServiceBooks;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@DisplayName("Запуск теста JPARepository")
@DataJpaTest
@Import({ServiceBooks.class})
class BooksDaoJpaTest {

    private static final long EXPECTED_BOOKS_COUNT = 8;
    private static final long FIRST_BOOKS_ID = 1L;


    @Autowired
    private TestEntityManager em;
    @Autowired
    private ServiceBooks serviceBooks;


    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @DisplayName("Количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        Long actualBooksCount = serviceBooks.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавление книг в БД")
    @Test
    void shouldInsertBooks() {
        Books expectedBooks = new Books("Война и мир");
        serviceBooks.save(expectedBooks);
        Books actualBooks = serviceBooks.getById(expectedBooks.getId()).get();
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @DisplayName("возвращение книги по его id")
    @Test
    void shouldReturnExpectedBooksById() {
        val optionalActualBooks = serviceBooks.getById(FIRST_BOOKS_ID);
        val expectedStudent = em.find(Books.class, FIRST_BOOKS_ID);
        assertThat(optionalActualBooks).isPresent().get().usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBooksById() {
        var book = serviceBooks.getById(FIRST_BOOKS_ID);
        assertThatCode(() -> book.get())
                .doesNotThrowAnyException();

        serviceBooks.deleteById(book.get().getId());
        Long actualBooksCount = serviceBooks.count();
        assertThat(actualBooksCount).isEqualTo(7L);
    }

    @DisplayName("возвращать список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        List<Books> actualBooksList = serviceBooks.getAll();
        assertNotNull("Обьект возвращает null проверьте данные", actualBooksList);
    }

}
