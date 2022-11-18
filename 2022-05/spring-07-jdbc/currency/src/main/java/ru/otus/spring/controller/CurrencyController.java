package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.CurrencyScheduled;
import ru.otus.spring.dao.service.ServiceCurrencyRate;
import ru.otus.spring.dao.service.ServiceCurrencyType;
import ru.otus.spring.model.CurrencyRateDb;
import ru.otus.spring.model.CurrencyTypeDb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(title = "Sample API", version = "v1")
)
@RestController
public class CurrencyController {

    private final ServiceCurrencyRate serviceCurrencyRate;
    private final CurrencyScheduled currencyScheduled;

    private final ServiceCurrencyType serviceCurrencyType;


    SimpleDateFormat formatter_dd_MM_yyyy = new SimpleDateFormat("dd.MM.yyyy");

    public CurrencyController(ServiceCurrencyRate serviceCurrencyRate, CurrencyScheduled currencyScheduled, ServiceCurrencyType serviceCurrencyType) {
        this.serviceCurrencyRate = serviceCurrencyRate;
        this.currencyScheduled = currencyScheduled;
        this.serviceCurrencyType = serviceCurrencyType;
    }

    @Operation(
            summary = "Получение курса на дату",
            description = "Получение курса на дату в формате dd.mm.yyyy .",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestData.class, required = true)),
                    required = true))
    @PostMapping("/api/all_currency_date")
    public List<CurrencyRateDb> currency(RequestData rd) {
        return serviceCurrencyRate.findByDate(currencyData(rd.data));
    }

    @Operation(
            summary = "Получение курса на дату по конкретной валюте",
            description = "Получение курса на дату в формате dd.mm.yyyy и валютой например USD.",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestDataType.class, required = true)),
                    required = true))
    @PostMapping("/api/currency_date_type")
    public List<CurrencyRateDb> indexPage(RequestDataType rdt) {
        return serviceCurrencyRate.findByDateType(currencyData(rdt.data), rdt.type.toUpperCase());
    }

    @Operation(
            summary = "Загрузить курсы сейчас",
            description = "Запускает загрузку курсов немедленно на текущую дату.")
    @GetMapping("/api/download_currency_now")
    public String currencyNow() {
        return  "Загружено за "+ currencyScheduled.downloadCurrency(formatter_dd_MM_yyyy.format(new Date()));
    }

    @Operation(
            summary = "Фильтр загрузки курсов",
            description = "Выводит фильтр загрузки курсов.")
    @GetMapping("/api/currency_filte")
    public List<CurrencyTypeDb>  currencyFilter() {
        return serviceCurrencyType.getAll();
    }

    private Date currencyData(String date) {
        Date currencyData;
        try {
            currencyData = formatter_dd_MM_yyyy.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Не верная дата.");
        }
        return currencyData;
    }


}
