package ru.otus.spring.dao.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CurrencyRateDao;
import ru.otus.spring.model.CurrencyRateDb;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ServiceCurrencyRateImp implements ServiceCurrencyRate {

    final CurrencyRateDao currencyRateDao;

    public ServiceCurrencyRateImp(CurrencyRateDao currencyRateDao) {
        this.currencyRateDao = currencyRateDao;
    }

    @Override
    public List<CurrencyRateDb> getAll() {
        return currencyRateDao.getAll();
    }

    @Override
    public List<CurrencyRateDb> findByDate(Date curDate) {
        return currencyRateDao.findByDate(curDate);
    }

    @Transactional
    @Override
    public void save(CurrencyRateDb cr) {
        currencyRateDao.save(cr);
    }

    @Override
    public List<CurrencyRateDb> findByDateType(Date curDate, String type) {
        return currencyRateDao.findByDateType(curDate, type);
    }
}
