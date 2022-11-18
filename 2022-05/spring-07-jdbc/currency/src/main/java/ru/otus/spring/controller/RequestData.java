package ru.otus.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema( description = "Контейнер загрузки валюты")
public class RequestData {
    @Schema( description = "Дата валюты.")
    @JsonProperty("data")
    public String data;
}
