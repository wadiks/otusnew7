package ru.otus.spring.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.CurrencyTypeDb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@AllArgsConstructor
@Repository
public class CurrencyTypeDaoImp implements CurrencyTypeDao {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public List<CurrencyTypeDb> getAll() {
        return em.createQuery("select  ct from CurrencyTypeDb ct ", CurrencyTypeDb.class)
                .getResultList();
    }

}
