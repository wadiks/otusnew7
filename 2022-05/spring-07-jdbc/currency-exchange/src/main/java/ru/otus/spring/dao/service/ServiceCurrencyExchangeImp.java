package ru.otus.spring.dao.service;

import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.CurrencyExchangeDao;
import ru.otus.spring.model.CurrencyExchange;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
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

    @Transactional
    @Override
    public CurrencyExchange insert(CurrencyExchange entity) {
        var seq = em.createNativeQuery("select NEXT VALUE FOR currency_sequence from dual")
                .getSingleResult();
        em.createNativeQuery("insert into currency_exchange (id, user_deposit, currency_type, currency_deposit) " +
                        "values (:id, :us, :ct, :cd)")
                .setParameter("id", seq)
                .setParameter("us", entity.getUserDeposit())
                .setParameter("ct", entity.getCurrencyType())
                .setParameter("cd", entity.getCurrencyDeposit())
                .executeUpdate();
        var ret = entity;
        ret.setId(Long.valueOf(seq.toString()));
        return ret;
    }

    @Override
    public void delete(CurrencyExchange entity) {
        currencyExchangeDao.delete(entity);
    }

    @Override
    public List<CurrencyExchange> getAllByUserDeposit(long id) {
        return currencyExchangeDao.getAllByUserDeposit(id);
    }

    @Override
    public List<CurrencyExchange> getCurrencyTypeByUserDeposit(long id, String type) {
        return currencyExchangeDao.getCurrencyTypeByUserDeposit(id, type);
    }

}
