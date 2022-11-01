package ru.otus.spring.dao.service;

import ru.otus.spring.model.CurrencyRateDb;

import java.util.Date;
import java.util.List;

public interface ServiceCurrencyRate {
    List<CurrencyRateDb> getAll();

    List<CurrencyRateDb> findByDate(Date curDate);

    void save (CurrencyRateDb cr);

    List<CurrencyRateDb> findByDateType(Date curDate , String type);

}
