package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.model.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeDao extends CrudRepository<CurrencyExchange, Long> {

    @Override
    List<CurrencyExchange> findAll();

    @Query("select  ce from CurrencyExchange ce where ce.userDeposit = :userId")
    List<CurrencyExchange> getAllByUserDeposit(@Param("userId") Long id);

    @Query("select  ce from CurrencyExchange ce where ce.userDeposit = :userId and ce.currencyType = :type")
    List<CurrencyExchange> getCurrencyTypeByUserDeposit(@Param("userId") Long id, @Param("type") String type);

}
