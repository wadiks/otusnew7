package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public interface PersonDao {
//тест сохраненния
    Person findByName(String name);
}
