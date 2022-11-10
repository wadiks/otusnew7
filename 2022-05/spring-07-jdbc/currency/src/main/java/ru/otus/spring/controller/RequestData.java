package ru.otus.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RequestContainerData", description = "Контейнер загрузки валюты")
public class RequestData {
    @JsonProperty("data")
    public String data;
}
