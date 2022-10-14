package ru.otus.spring.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.model.Security;

@RepositoryRestResource(path = "security")
public interface SecurityDao extends MongoRepository<Security, String> {

    List<Security> findAll();

    @RestResource(path = "user", rel = "user")
    Security findByUser(String user);
}
