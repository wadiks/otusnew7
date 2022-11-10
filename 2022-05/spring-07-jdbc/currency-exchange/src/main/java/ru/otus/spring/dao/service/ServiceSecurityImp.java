package ru.otus.spring.dao.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.SecurityDao;
import ru.otus.spring.model.Security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ServiceSecurityImp implements ServiceSecurity {

    @PersistenceContext
    private final EntityManager em;
    final SecurityDao securityDao;

    public ServiceSecurityImp(EntityManager em, SecurityDao securityDao) {
        this.em = em;
        this.securityDao = securityDao;
    }

    @Override
    public List<Security> findAll() {
        return  securityDao.findAll();
    }

    @Override
    public Security findByUser(String user) {
        final var rezult = em.createQuery("select  s from Security s where s.userSecurity = :user", Security.class)
                .setParameter("user", user)
                .getResultList();
        return (null!= rezult && !rezult.isEmpty() ? rezult.get(0) :null);
    }
}
