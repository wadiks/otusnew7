package ru.otus.spring.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.microservice.domain.Person;
import ru.otus.spring.microservice.repostory.PersonRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class App {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @PostConstruct
    public void init() {
        for(int i = 0 ; i < 18; ++i) {
            repository.save(new Person("Пёрсона №" + i));
        }
    }
}
