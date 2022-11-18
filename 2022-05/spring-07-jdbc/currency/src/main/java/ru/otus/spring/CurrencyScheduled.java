package ru.otus.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dao.service.ServiceCurrencyRate;
import ru.otus.spring.dao.service.ServiceCurrencyType;
import ru.otus.spring.model.CurrencyRateContainer;
import ru.otus.spring.model.CurrencyRateDb;
import ru.otus.spring.model.CurrencyRateWeb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class CurrencyScheduled {

    ServiceCurrencyType serviceCurrencyType;

    ServiceCurrencyRate serviceCurrencyRate;

    public CurrencyScheduled(ServiceCurrencyType serviceCurrencyType, ServiceCurrencyRate serviceCurrencyRate) {
        this.serviceCurrencyType = serviceCurrencyType;
        this.serviceCurrencyRate = serviceCurrencyRate;
    }

    private static final String URL = "https://cbr.ru/scripts/XML_daily.asp?date_req=";
    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

    @Scheduled(cron = "0 1 23 ? * *",zone = "Europe/Moscow")
    private void scheduled() {
        downloadCurrency(tomorrowDate());
    }

    public String downloadCurrency (String data){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + data, String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            final var curr = mapper(responseEntity.getBody());
            final var provDate = curr.getDate();
            if (serviceCurrencyRate.findByDate(provDate).isEmpty()) {
                var crdb = filterCourse(curr);
                crdb.forEach(cr ->  serviceCurrencyRate.save(cr));
            }
            return format1.format(curr.getDate());
        }
        return null;
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
        return format1.format(cal.getTime());
    }


}
