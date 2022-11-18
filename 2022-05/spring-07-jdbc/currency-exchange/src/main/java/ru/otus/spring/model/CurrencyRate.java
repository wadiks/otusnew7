package ru.otus.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CurrencyRate implements Serializable {

    /**
     * Тип валюты  3 символа
     */
    @JsonProperty("type")
    public String type;

    /**
     * Валюта на конкретную дату
     */
    @JsonProperty("curDate")
    public Date curDate;

    /**
     * значение валюты
     */
    @JsonProperty("value")
    public BigDecimal value;
}