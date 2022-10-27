package ru.otus.spring.dao;

import ru.otus.spring.model.bd.CurrencyRate;
import ru.otus.spring.model.bd.CurrencyType;

import java.util.List;

public interface CurrencyRateDao {
    List<CurrencyRate> getAll();

    List<CurrencyRate> findByDate();

    CurrencyRate save (CurrencyRate cr);

}
