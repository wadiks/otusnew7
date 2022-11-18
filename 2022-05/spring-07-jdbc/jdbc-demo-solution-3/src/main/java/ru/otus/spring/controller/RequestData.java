package ru.otus.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "RequestContainerData", description = "Контейнер обмена валюты")
public class RequestData {
    @JsonProperty("value")
    public BigDecimal value;
}