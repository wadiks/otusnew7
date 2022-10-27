package ru.otus.spring.dao;

import ru.otus.spring.model.bd.CurrencyType;

import java.util.List;

public interface CurrencyTypeDao {

    List<CurrencyType> getAll();
}
