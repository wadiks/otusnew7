package ru.otus.spring.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.Const;
import ru.otus.spring.model.CurrencyRateContainer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class SendCurrencyResponse {

    public CurrencyRateContainer send() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", nowDate());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(Const.URL_REQUEST_CURRENCY, request, String.class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {

            return mapper(response.getBody());
        }
        return null; /* сдесь надо описать ошибку*/
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

    private String nowDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        return format1.format(cal.getTime());
    }
}
