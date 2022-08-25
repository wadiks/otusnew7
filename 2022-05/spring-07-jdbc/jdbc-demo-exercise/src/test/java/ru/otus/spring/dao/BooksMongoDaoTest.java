package ru.otus.spring.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.otus.spring.model.Books;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@DisplayName("Запуск теста MongoRepository")
@SpringBootTest
class BooksMongoDaoTest {

    private static final long EXPECTED_BOOKS_COUNT = 8;
    private static final long FIRST_BOOKS_ID = 1L;

    @Autowired
    private BooksDao booksDao;


    @DisplayName("Количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        Long actualBooksCount = booksDao.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавление книг в БД")
    @Test
    void shouldInsertBooks() {
        Books expectedBooks = new Books("Война и мир");
        booksDao.save(expectedBooks);
        Books actualBooks = booksDao.findById(expectedBooks.getId()).get();
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @DisplayName("возвращение книги по его id")
    @Test
    void shouldReturnExpectedBooksById() {
        val optionalActualBooks = booksDao.findById(String.valueOf(FIRST_BOOKS_ID));
        Assert.notNull(optionalActualBooks, "Должен придти не нулевой элемент.");
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBooksById() {
        var book = booksDao.findById(String.valueOf(FIRST_BOOKS_ID));
        assertThatCode(() -> book.get())
                .doesNotThrowAnyException();
        booksDao.deleteById(book.get().getId());
        Long actualBooksCount = booksDao.count();
        assertThat(actualBooksCount).isEqualTo(7L);
    }

    @DisplayName("возвращать список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        List<Books> actualBooksList = booksDao.findAll();
        assertNotNull("Обьект возвращает null проверьте данные", actualBooksList);
    }

}
