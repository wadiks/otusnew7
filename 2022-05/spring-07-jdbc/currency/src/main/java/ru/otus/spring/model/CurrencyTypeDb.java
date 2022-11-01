package ru.otus.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table( name = "filter_currency")
public class CurrencyTypeDb implements Serializable {

    @Id
    private String id;

    private String name;

    @Column(name = "name_decoding")
    private String courseValue;
}

