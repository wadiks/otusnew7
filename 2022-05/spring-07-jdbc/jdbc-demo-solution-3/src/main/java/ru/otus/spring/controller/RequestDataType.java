package ru.otus.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RequestContainerDataType", description = "Контейнер обмена валюты с типом")
public class RequestDataType extends RequestData {
    @JsonProperty("type_to")
    public String typeTo;
    @JsonProperty("type_from")
    public String typeFrom;
}
