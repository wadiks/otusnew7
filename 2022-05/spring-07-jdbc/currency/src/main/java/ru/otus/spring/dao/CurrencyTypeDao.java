package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.CurrencyTypeDb;

import java.util.List;

public interface CurrencyTypeDao extends JpaRepository<CurrencyTypeDb,Long> {

}
