package ru.otus.spring.dao;

import ru.otus.spring.model.CurrencyTypeDb;

import java.util.List;

public interface CurrencyTypeDao {

    List<CurrencyTypeDb> getAll();
}
