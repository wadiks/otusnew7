package ru.otus.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RequestContainerDataType", description = "Контейнер загрузки валюты с типом")
public class RequestDataType extends RequestData {
    @JsonProperty("type")
    public String type;
}
