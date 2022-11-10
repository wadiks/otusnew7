package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.CurrencyExchange;
import ru.otus.spring.model.Security;

import java.util.List;

public interface CurrencyExchangeDao extends CrudRepository<CurrencyExchange, Long> {

    @Override
    List<CurrencyExchange> findAll();
}
