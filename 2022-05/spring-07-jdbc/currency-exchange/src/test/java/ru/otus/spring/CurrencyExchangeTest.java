package ru.otus.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dao.service.ServiceUserDeposit;
import ru.otus.spring.model.CurrencyRateContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Запуск теста")
@SpringBootTest
public class CurrencyExchangeTest {

    final String URL = "http://localhost:8888/api/all_currency_date";

    @Autowired
    ServiceUserDeposit serviceUserDeposit;


    @DisplayName("Запрос к другой программе")
    @Test
    void sendCurrency() {
        var rezult = send();
        assertEquals(HttpStatus.OK , rezult.getStatusCode());
    }

    @DisplayName("Запрос к другой программе и разбор ответа")
    @Test
    void sendMapper() {
        var result = send();
        if (HttpStatus.OK.equals(result.getStatusCode())) {
           var rezMap = mapper(result.getBody());
            assertNotNull(rezMap);
        }
    }

public ResponseEntity<String> send() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    map.add("data", "01.11.2022");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    ResponseEntity<String> response = restTemplate.postForEntity( URL, request , String.class );
    return response;
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

}


