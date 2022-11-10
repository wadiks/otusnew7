package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.dao.service.ServiceSecurity;
import ru.otus.spring.dao.service.ServiceUserDeposit;
import ru.otus.spring.model.Curr;
import ru.otus.spring.model.Security;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@DisplayName("Запуск теста Перевода курса")
@SpringBootTest
public class CurrencyExhangeTest {

        @Autowired
        ServiceUserDeposit serviceUserDeposit;

        @Autowired
        ServiceSecurity serviceSecurity;

        @DisplayName("Все данные из пользовательносго репозитория")
        @Test
        void returnUserRepository() {
            final var rez = serviceUserDeposit.getAll();
            System.out.println("rez = " + rez);
        }

        @DisplayName("Все данные секюрити")
        @Test
        void returnUserSecutity() {

            final var rez = serviceSecurity.findAll();
            System.out.println("rez security = " + rez);
        }


        @DisplayName("Все запрос к другой программе")
        @Test
        void returnCurrency() {
            var sec = new Security();
            var cur = new Curr();
            System.out.println("serviceUserDeposit = " + sec + cur);
 /*    var rezult =   send();
        System.out.println("rezult = " + rezult);*/
        }


/*
    public CurrencyRateDbExchange send (){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8888/api/all_currency_date?data="+ nowDate(), String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return mapper(responseEntity.getBody());
        }
        return null;
    }

    private CurrencyRateDbExchange mapper(String xml) {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        try {
            return mapper.readValue(xml, CurrencyRateDbExchange.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

*/

        private String nowDate() {
            Calendar cal = Calendar.getInstance();
            // cal.add(Calendar.DATE, 1);
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
            return format1.format(cal.getTime());
        }


/*
    private static final long EXPECTED_BOOKS_COUNT = 8;
    private static final long FIRST_BOOKS_ID = 1L;

    private List<Books> book = new ArrayList<>();

    @Autowired
    private BooksDao booksDao;

    @BeforeEach
    void start() {
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
        Books expectedBooks = new Books(null,"Война и мир",null,null);
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

--------------


package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.controller.BookController;

@DisplayName("Запуск теста Security")
@ExtendWith({SpringExtension.class})
@WebMvcTest({BookController.class})
@SpringBootTest
class BooksMongoDaoTest {
    @Autowired
    private MockMvc mockMvc;

    BooksMongoDaoTest() {
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testBookDeleteAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bookDelete", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBookEditAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/edit", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBookInsertAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/insert", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
---------------------------





 */
    }


