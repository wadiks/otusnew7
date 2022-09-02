package ru.otus.spring.dao;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@DisplayName("Запуск теста MongoRepository")
@SpringBootTest
class BooksMongoDaoTest {

    private static final long EXPECTED_BOOKS_COUNT = 8;
    private static final long FIRST_BOOKS_ID = 1L;

    private List<Books> book = new ArrayList<>();

    @Autowired
    private BooksDao booksDao;

    @BeforeEach
    void strart() {
        book.add(new Books() {{
            setId(1L);
            setName("Мидлмарч");
            setGenres(new ArrayList<Genre>() {{
                add(new Genre("Детектив"));
            }});
            setAuthors(new ArrayList<Authors>() {{
                add(new Authors("Джордж", "Элиот"));
                add(new Authors("Вирджиния", "Вулф"));
            }});
        }});
    }


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
        val optionalActualBooks = booksDao.findById(FIRST_BOOKS_ID);
        if (optionalActualBooks.isEmpty()) System.out.println("Пустой");
        else System.out.println("optionalActualBooks = " + optionalActualBooks.get());
        Assert.notNull(optionalActualBooks, "Должен придти не нулевой элемент.");

    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBooksById() {
        var book = booksDao.findById(FIRST_BOOKS_ID);
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

    @Test
    void testBooksDao() {
        final var rez = book.get(0).getGenres().stream().map(m -> m.getName()).collect(Collectors.toSet());
        var rez1 = String.join(",", rez);
        System.out.println("rez1 = " + rez1);

        final var rezA = book.get(0).getAuthors().stream().map(m -> m.getName() + " " + m.getSurname()).collect(Collectors.toSet());
        var rezA1 = String.join(",", rezA);
        System.out.println("rezA1 = " + rezA1);

        rez1 = "Фантастика , Ужас, Милодрамма";
        rezA1 = "Джордж Элиот,Гарри Потер";

        List<Genre> names = Stream.of(rez1.split(","))
                .map(m -> m.trim())
                .map(Genre::new)
                .collect(Collectors.toList());
        System.out.println("names = " + names);

        List<Authors> rez121 = Stream.of(rezA1.split(","))
                .map(m -> {
                    var array = m.trim().split(" ");
                    if ((null != array[0] && !array[0].isEmpty()) && (null != array[1] && !array[1].isEmpty()))
                        return new Authors(array[0], array[1]);
                    else return null;
                })
                .collect(Collectors.toList());
        System.out.println("rez121 = " + rez121);
    }
}