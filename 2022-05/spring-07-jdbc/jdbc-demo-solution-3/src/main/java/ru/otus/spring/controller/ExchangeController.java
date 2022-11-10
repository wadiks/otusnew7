package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.CurrencyExchangeService;
import ru.otus.spring.domain.SendCurrencyResponse;
import ru.otus.spring.model.Curr;
import ru.otus.spring.model.UserDeposit;


@RestController
public class ExchangeController {

    private final SendCurrencyResponse sendCurrencyResponse;
    private final CurrencyExchangeService currencyExchangeService;

    public ExchangeController(SendCurrencyResponse sendCurrencyResponse, CurrencyExchangeService currencyExchangeService) {
        this.sendCurrencyResponse = sendCurrencyResponse;
        this.currencyExchangeService = currencyExchangeService;
    }

    /**
     * метод получения курса на текущюю дату
     */
    @GetMapping("/api/all_currency")
    public Curr currency() {

        final var rez = sendCurrencyResponse.send();
        System.out.println("rez = " + rez);
        return rez;
    }

    /**
     * Посмотреть мои депозиты
     */
    @GetMapping("/api/all_deposit")
    public UserDeposit allDeposit() {
        return currencyExchangeService.allDeposit();
    }


    /**
     * Добавить рублевый депозит
     */
    @GetMapping("/api/add_deposit")
    public UserDeposit addDeposit(RequestData rd) {
        return currencyExchangeService.addDeposit(rd.getValue());
    }


    /**
     * Снять рублевый депозит
     */
    @GetMapping("/api/subtract_deposit")
    public UserDeposit subtractDeposit(RequestData rd) {
        return currencyExchangeService.subtractDeposit(rd.getValue());
    }


    /**
     * Конвертировать депозит
     */
    @GetMapping("/api/currency_exchange")
    public UserDeposit currencyExchange(RequestDataType rdt) {
        return currencyExchangeService.currencyExchange(rdt);
    }

/*

    private final ServiceCurrencyRate serviceCurrencyRate;
    SimpleDateFormat formatter_dd_MM_yyyy = new SimpleDateFormat("dd.MM.yyyy");

    public CurrencyController(ServiceCurrencyRate serviceCurrencyRate) {
        this.serviceCurrencyRate = serviceCurrencyRate;
    }

    @GetMapping("/api/all_currency_date")
    public List<CurrencyRateDb> currency(RequestData rd) {
        return serviceCurrencyRate.findByDate(currencyData(rd.data));
    }

    @GetMapping("/api/currency_date_type")
    public List<CurrencyRateDb> indexPage(RequestDataType rdt) {
        return serviceCurrencyRate.findByDateType(currencyData(rdt.data), rdt.type.toUpperCase());
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


 */

}
