package ru.otus.spring.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.Const;
import ru.otus.spring.model.Curr;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class SendCurrencyResponse {

    public Curr send (){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(Const.URL_REQUEST_CURRENCY+ nowDate(), String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return mapper(responseEntity.getBody());
        }
        return null;
    }

    private Curr mapper(String xml) {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        try {
            return mapper.readValue(xml, Curr.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String nowDate() {
        Calendar cal = Calendar.getInstance();
       // cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        return format1.format(cal.getTime());
    }


}
