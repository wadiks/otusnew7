package ru.otus.spring.dao.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CurrencyExchangeDao;
import ru.otus.spring.model.CurrencyExchange;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCurrencyExchangeImp implements ServiceCurrencyExchange {

    @PersistenceContext
    private final EntityManager em;

    final CurrencyExchangeDao currencyExchangeDao;

    public ServiceCurrencyExchangeImp(EntityManager em, CurrencyExchangeDao currencyExchangeDao) {
        this.em = em;
        this.currencyExchangeDao = currencyExchangeDao;
    }

    @Override
    public Optional<CurrencyExchange> getById(long id) {
        return currencyExchangeDao.findById(id);
    }

    @Override
    public List<CurrencyExchange> getAll() {
        return currencyExchangeDao.findAll();
    }

    @Transactional
    @Override
    public void save(CurrencyExchange entity) {
        currencyExchangeDao.save(entity);
    }

    @Override
    public List<CurrencyExchange> getAllByUserDeposit(long id) {
        return em.createQuery("select  ce from CurrencyExchange ce where ce.userDeposit = :userId", CurrencyExchange.class)
                .setParameter("userId", id)
                .getResultList();
    }

    @Override
    public List<CurrencyExchange> getCurrencyTypeByUserDeposit(long id, String type) {
        return em.createQuery("select  ce from CurrencyExchange ce where ce.userDeposit = :userId and ce.currencyType = :type ", CurrencyExchange.class)
                .setParameter("userId", id)
                .setParameter("type", type)
                .getResultList();
    }


}
