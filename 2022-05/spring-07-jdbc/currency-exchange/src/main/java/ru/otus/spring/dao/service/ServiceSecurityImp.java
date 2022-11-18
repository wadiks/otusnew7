package ru.otus.spring.dao.service;

import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.SecurityDao;
import ru.otus.spring.model.Security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
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
        return securityDao.findAll();
    }

    @Override
    public Security findByUser(String user) {
        final var rezult = securityDao.findByUser(user);
        return (null != rezult && !rezult.isEmpty() ? rezult.get(0) : null);
    }

    @Transactional
    @Override
    public void sav(Security entity) {
        securityDao.save(entity);
    }


    @Transactional
    @Override
    public Security insert(Security entity) {
        var seq = em.createNativeQuery("select NEXT VALUE FOR security_sequence from dual")
                .getSingleResult();
        em.createNativeQuery("insert into user_security (id, user_security,password_security,role_security ) " +
                        "values (:id, :us, :ps, :rs)")
                .setParameter("id", seq)
                .setParameter("us", entity.getUserSecurity())
                .setParameter("ps", entity.getPasswordSecurity())
                .setParameter("rs", entity.getRoleSecurity())
                .executeUpdate();
        var ret = entity;
        ret.setId(Long.valueOf(seq.toString()));
        return ret;
    }

}
