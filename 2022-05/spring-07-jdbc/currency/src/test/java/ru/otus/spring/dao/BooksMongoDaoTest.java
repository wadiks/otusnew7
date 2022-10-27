package ru.otus.spring.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.model.web.CurrencyRateContainer;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;



@DisplayName("Запуск теста MongoRepository")
@SpringBootTest
class BooksMongoDaoTest {

    ObjectMapper xlmMap;
    private static final String URL = "https://cbr.ru/scripts/XML_daily.asp?date_req=22.08.2022";

    @BeforeEach
    void start() {
        xlmMap = new XmlMapper();
        xlmMap.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @DisplayName("Количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @DisplayName("добавление книг в БД")
    @Test
    void shouldInsertBooks() throws IOException {
        try (final InputStream is = getClass().getResourceAsStream("/model/Course.xml")) {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper mapper = new XmlMapper(module);
            CurrencyRateContainer cur = mapper.readValue(is, CurrencyRateContainer.class);
            System.out.println("cur.toString = " + cur.toString());
        }
    }

    @DisplayName("возвращение книги по его id")
    @Test
    void shouldReturnExpectedBooksById() {


    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBooksById() {

    }

    @DisplayName("возвращать список книг")
    @Test
    void shouldReturnExpectedBooksList() {

    }

    @Test
    void testBooksDao() {

    }
}
