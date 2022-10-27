package ru.otus.spring.model.bd;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(schema = "insis_life_alf", name = "ht_currency_type")
public class CurrencyType implements Serializable {

    @Id
    private String id;

    private String name;

    @Column(name = "course_value")
    private String courseValue;

}
