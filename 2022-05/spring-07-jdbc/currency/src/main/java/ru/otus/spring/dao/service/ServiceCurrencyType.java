package ru.otus.spring.dao.service;

import ru.otus.spring.model.CurrencyTypeDb;

import java.util.List;

public interface ServiceCurrencyType {

    List<CurrencyTypeDb> getAll();
}
