package ru.otus.spring.dao.service;

import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.UserDepositDao;
import ru.otus.spring.model.UserDeposit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ServiceUserDepositImp implements ServiceUserDeposit {

    @PersistenceContext
    private final EntityManager em;
    final UserDepositDao userDepositDao;

    public ServiceUserDepositImp(EntityManager em, UserDepositDao userDepositDao) {
        this.em = em;
        this.userDepositDao = userDepositDao;
    }

    @Override
    public Optional<UserDeposit> getById(long id) {
        return userDepositDao.findById(id);
    }

    @Override
    public List<UserDeposit> getAll() {
        return userDepositDao.findAll();
    }

    @Transactional
    @Override
    public void save(UserDeposit entity) {
        userDepositDao.save(entity);
    }

    @Transactional
    @Override
    public void insert(UserDeposit entity) {
        em.createNativeQuery("insert into user_deposit (user_id, deposit) values (:id, :ud)")
                .setParameter("id", entity.getUserId())
                .setParameter("ud", entity.getDeposit())
                .executeUpdate();
    }


}
