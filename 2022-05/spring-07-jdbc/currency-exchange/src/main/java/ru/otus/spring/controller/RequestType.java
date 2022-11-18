package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Schema( description = "Контейнер обмена валюты с типом")
public class RequestType extends RequestSumma {

    /**
     * перевести к какой-то валюте (купить)
     */
    @Schema( description = "Купить валюту.")
     public String typeTo;

    /**
     * вывести из этой валютым(продать)
     */
    @Schema( description = "Продать валюту.")
    public String typeFrom;
}
