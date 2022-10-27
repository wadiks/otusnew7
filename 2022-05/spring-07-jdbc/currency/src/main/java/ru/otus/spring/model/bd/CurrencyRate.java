package ru.otus.spring.model.bd;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@IdClass(CurrencyRate.CurrencyRatePK.class)
@Table(name = "course")
public class CurrencyRate implements Serializable {

    public static class CurrencyRatePK implements Serializable {

        private String type;

        private Date curDate;

        private String countryId;
    }

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