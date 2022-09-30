package ru.otus.spring.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Security;

public interface SecurityDao extends MongoRepository<Security, String> {
    List<Security> findAll();
    Security findByUser(String user);
}
