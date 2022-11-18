package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema( description = "Контейнер обмена валюты")
public class RequestSumma {
    @Schema( description = "Сумма.")
    public Integer amount;
}
