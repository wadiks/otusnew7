package ru.otus.spring.dao.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.UserDepositDao;
import ru.otus.spring.model.UserDeposit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserDepositImp implements ServiceUserDeposit {

    final UserDepositDao userDepositDao;

    public ServiceUserDepositImp(UserDepositDao userDepositDao) {
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

}
