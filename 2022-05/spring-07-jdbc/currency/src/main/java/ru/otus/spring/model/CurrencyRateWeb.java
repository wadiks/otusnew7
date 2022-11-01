package ru.otus.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ru.otus.spring.json.BigDecimalDeserializer;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@JsonRootName("Valute")
public class CurrencyRateWeb implements Serializable {
    /**
     * ID Валюты
     */
    @JsonProperty("ID")
    private String id;

    /**
     * Номер валюты
     */
    @JsonProperty("NumCode")
    private String numCode;
    /**
     * Название валюты
     */
    @JsonProperty("CharCode")
    private String charCode;
    /**
     * Номинал
     */
    @JsonProperty("Nominal")
    private BigInteger nominal;
    /**
     * Расшифровка валюты
     */
    @JsonProperty("Name")
    private String name;
    /**
     * курс валюты
     */
    @JsonProperty("Value")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal value;
}
