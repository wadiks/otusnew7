package ru.otus.spring.model.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonRootName("ValCurs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateContainer implements Serializable {
    /**
     * Курс на дату
     */
    @JsonProperty("Date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Moscow")
    private Date date;

    /**
     * Наименование данных
     */
    @JsonProperty("name")
    private String name;

    /**
     * Список курсов
     */
    @JsonProperty("Valute")
    private List<CurrencyRate> rates;

}