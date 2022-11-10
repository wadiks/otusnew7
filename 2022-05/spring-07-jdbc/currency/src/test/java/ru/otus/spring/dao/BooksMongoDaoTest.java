package ru.otus.spring.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dao.service.ServiceCurrencyRate;
import ru.otus.spring.dao.service.ServiceCurrencyType;
import ru.otus.spring.model.CurrencyRateContainer;
import ru.otus.spring.model.CurrencyRateDb;
import ru.otus.spring.model.CurrencyRateWeb;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Запуск теста загрузки курсов")
@SpringBootTest
class BooksMongoDaoTest {

    @Autowired
    ServiceCurrencyType serviceCurrencyType;

    @Autowired
    ServiceCurrencyRate serviceCurrencyRate;

    ObjectMapper xlmMap;
    private static final String URL = "https://cbr.ru/scripts/XML_daily.asp?date_req=";

    @BeforeEach
    void start() {
        xlmMap = new XmlMapper();
        xlmMap.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @DisplayName("Обращение в ЦБР")
    @Test
    void shouldReturnExpectedBooksCount() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + tomorrowDate(), String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @DisplayName("Преобразование из json в обьект после обращения из ЦБР")
    @Test
    void shouldInsertBooks() throws IOException {
        try (final InputStream is = getClass().getResourceAsStream("/model/Course.xml")) {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper mapper = new XmlMapper(module);
            CurrencyRateContainer cur = mapper.readValue(is, CurrencyRateContainer.class);
            assertNotNull(cur);
        }
    }

    @DisplayName("возвращение книги по его id")
    @Test
    void shouldReturnExpectedBooksById() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + tomorrowDate(), String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            final var curr = mapper(responseEntity.getBody());
            final var provDate = curr.getDate();
            if (serviceCurrencyRate.findByDate(provDate).isEmpty()) {
                var rezc =  filterCourse(curr);
                System.out.println("provDate = " + rezc);
            }

        }
    }

    private List<CurrencyRateDb> filterCourse(CurrencyRateContainer crc) {
        final Date curDate;
        curDate = crc.getDate();
        if (null == curDate) throw new RuntimeException("Не указана дата пакета курсов валют.");
        var currencyType = serviceCurrencyType.getAll();
        List<CurrencyRateDb> ratesList = new ArrayList<>();
        if (!currencyType.isEmpty()) {
            currencyType.forEach(f -> {
                crc.getRates().stream()
                        .filter(filter -> {
                            if (filter.getCharCode().equals(f.getName())) {
                                return true;
                            } else {
                                return false;
                            }
                        })
                        .forEach(cr -> ratesList.add(toRate(cr, curDate)));
            });
        return ratesList;
        }
        return null;
    }

    public static CurrencyRateDb toRate(final CurrencyRateWeb rate,
                                        final Date date) {
        if (null == rate)
            return null;
        final var cRate = new CurrencyRateDb();
        cRate.setType(rate.getCharCode());
        cRate.setCurDate(date);
        cRate.setValue(rate.getValue());
        return cRate;
    }


    private CurrencyRateContainer mapper(String xml) {

        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        try {
            return mapper.readValue(xml, CurrencyRateContainer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String tomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        return format1.format(cal.getTime());
    }

}
