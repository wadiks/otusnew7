package ru.otus.spring.dao.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CurrencyTypeDao;
import ru.otus.spring.model.CurrencyTypeDb;

import java.util.List;

@Service
public class ServiceCurrencyTypeImp implements ServiceCurrencyType {

  final CurrencyTypeDao currencyTypeDao;

    public ServiceCurrencyTypeImp(CurrencyTypeDao currencyTypeDao) {
        this.currencyTypeDao = currencyTypeDao;
    }

    @Override
    public List<CurrencyTypeDb> getAll() {
      return  currencyTypeDao.getAll();
    }
}
