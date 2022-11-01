package ru.otus.spring.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "currency")
public class CurrencyRateDb implements Serializable {

//schema = "CBR",

    /**
     * Тип валюты  3 символа
     */
    @Id
    @Column(name = "CURRENCY_TYPE")
    private String type;

    /**
     * Валюта на конкретную дату
     */
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "CURRENCY_DATE")
    private Date curDate;

    /**
     * значение валюты
     */
    @Column(name = "CURRENCY_VALUE")
    private BigDecimal value;



}