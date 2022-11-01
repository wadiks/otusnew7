package ru.otus.spring.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.CurrencyRateDb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Repository
public class CurrencyRateDaoImp implements CurrencyRateDao {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public List<CurrencyRateDb> getAll() {
        return em.createQuery("select  cr from CurrencyRateDb cr ", CurrencyRateDb.class)
                .getResultList();
    }

    @Override
    public List<CurrencyRateDb> findByDate(Date curDate) {
        return em.createQuery("select cr from CurrencyRateDb cr where cr.curDate = :curDate  ", CurrencyRateDb.class)
                .setParameter("curDate", curDate)
                .getResultList();
    }

    @Override
    public void save(CurrencyRateDb cr) {
        em.merge(cr);
    }

    @Override
    public List<CurrencyRateDb> findByDateType(Date curDate, String type) {
        return em.createQuery("select cr from CurrencyRateDb cr where cr.curDate = :curDate and cr.type = :type ", CurrencyRateDb.class)
                .setParameter("curDate", curDate)
                .setParameter("type", type)
                .getResultList();
    }
}
