package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.CurrencyExchangeService;
import ru.otus.spring.domain.SecurityService;
import ru.otus.spring.domain.SendCurrencyResponse;
import ru.otus.spring.model.CurrencyRateContainer;
import ru.otus.spring.model.Security;
import ru.otus.spring.model.UserDeposit;

import java.math.BigDecimal;


@RestController
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(title = "Sample API", version = "v1"),
        security = @SecurityRequirement(name = "basicAuth") 
)
public class ExchangeController {

    private final SendCurrencyResponse sendCurrencyResponse;
    private final CurrencyExchangeService currencyExchangeService;
    private final SecurityService securityService;

    public ExchangeController(SendCurrencyResponse sendCurrencyResponse, CurrencyExchangeService currencyExchangeService, SecurityService securityService) {
        this.sendCurrencyResponse = sendCurrencyResponse;
        this.currencyExchangeService = currencyExchangeService;
        this.securityService = securityService;
    }


    @Operation(
            summary = "Получение курса на текущюю дату",
            description = "Получение курса на текущюю дату.")
    @GetMapping("/api/all_currency")
    public CurrencyRateContainer currency() {
        final var rez = sendCurrencyResponse.send();
        return rez;
    }


    @Operation(
            summary = "Посмотреть мои депозиты",
            description = "Смотрит все депозиты пользователя включая валютные.")
    @GetMapping("/api/all_deposit")
    public UserDeposit allDeposit() {
        return currencyExchangeService.allDeposit();
    }



    @Operation(
            summary = "Пополнить рублевый депозит",
            description = "Пополнить рублевый депозит пользоввтеля.",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestSumma.class, required = true)),
                    required = true))
    @PostMapping("/api/add_deposit")
    public UserDeposit addDeposit(RequestSumma rd) {
        return currencyExchangeService.addDeposit(BigDecimal.valueOf(rd.getAmount()));
    }


    @Operation(
            summary = "Снять рублевый депозит",
            description = "Снять рублевый депозит пользователя.",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestSumma.class, required = true)),
                    required = true))
    @PostMapping("/api/subtract_deposit")
    public UserDeposit subtractDeposit(RequestSumma rd) {
        return currencyExchangeService.subtractDeposit(BigDecimal.valueOf(rd.getAmount()));
    }


    @Operation(
            summary = "Покупка, продажа валюты",
            description = "Заказ пользователя на покупку и продажу валюты.",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestType.class, required = true)),
                    required = true))
    @PostMapping("/api/currency_exchange")
    public UserDeposit currencyExchange(RequestType rdt) {
        return currencyExchangeService.currencyExchange(rdt);
    }


    @Operation(
            summary = "Зарегистрировать порльзователя",
            description = "Метод регистрации пользователей",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestSecurity.class, required = true)),
                    required = true))
    @PostMapping("/api/add_user")
    public Security addUser(RequestSecurity user) {
        return securityService.addUser(user);

    }
}
