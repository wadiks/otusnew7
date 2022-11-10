package ru.otus.spring.model;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Curr implements Serializable {

    /**
     * Тип валюты  3 символа
     */
    private String type;

    /**
     * Валюта на конкретную дату
     */
    private Date curDate;

    /**
     * значение валюты
     */
    private BigDecimal value;



}