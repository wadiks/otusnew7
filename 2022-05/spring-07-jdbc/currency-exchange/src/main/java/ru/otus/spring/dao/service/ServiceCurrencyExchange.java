package ru.otus.spring.dao.service;

import ru.otus.spring.model.CurrencyExchange;

import java.util.List;
import java.util.Optional;

public interface ServiceCurrencyExchange {

    Optional<CurrencyExchange> getById(long id);

    List<CurrencyExchange> getAllByUserDeposit(long id);

    List<CurrencyExchange> getCurrencyTypeByUserDeposit(long id, String type);

    List<CurrencyExchange> getAll();

    void save(CurrencyExchange entity);

    CurrencyExchange insert(CurrencyExchange entity);

    void delete(CurrencyExchange entity);
}
