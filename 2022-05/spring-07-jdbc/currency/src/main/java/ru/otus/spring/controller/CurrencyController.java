package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.CurrencyScheduled;
import ru.otus.spring.dao.service.ServiceCurrencyRate;
import ru.otus.spring.model.CurrencyRateDb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CurrencyController {

    private final ServiceCurrencyRate serviceCurrencyRate;
    private final CurrencyScheduled currencyScheduled;


    SimpleDateFormat formatter_dd_MM_yyyy = new SimpleDateFormat("dd.MM.yyyy");

    public CurrencyController(ServiceCurrencyRate serviceCurrencyRate, CurrencyScheduled currencyScheduled) {
        this.serviceCurrencyRate = serviceCurrencyRate;
        this.currencyScheduled = currencyScheduled;
    }

    @GetMapping("/api/all_currency_date")
    public List<CurrencyRateDb> currency(RequestData rd) {
        return serviceCurrencyRate.findByDate(currencyData(rd.data));
    }

    @GetMapping("/api/download_currency_now")
    public String currencyNow() {
        currencyScheduled.downloadCurrency(formatter_dd_MM_yyyy.format(new Date()));
        return "OK";
    }

    @GetMapping("/api/currency_date_type")
    public List<CurrencyRateDb> indexPage(RequestDataType rdt) {
        return serviceCurrencyRate.findByDateType(currencyData(rdt.data), rdt.type.toUpperCase());
    }

    private Date currencyData(String date) {
        Date currencyData;
        try {
            currencyData = formatter_dd_MM_yyyy.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Не верная дата.");
        }
        return currencyData;
    }


}
