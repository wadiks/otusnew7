package ru.otus.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonRootName("List")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateContainer implements Serializable {

    @JsonProperty("item")
    public List<CurrencyRate> currency;

}
