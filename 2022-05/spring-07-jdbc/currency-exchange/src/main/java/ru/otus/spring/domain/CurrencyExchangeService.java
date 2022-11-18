package ru.otus.spring.domain;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.controller.RequestType;
import ru.otus.spring.dao.service.ServiceCurrencyExchange;
import ru.otus.spring.dao.service.ServiceUserDeposit;
import ru.otus.spring.model.CurrencyExchange;
import ru.otus.spring.model.CurrencyRateContainer;
import ru.otus.spring.model.UserDeposit;
import ru.otus.spring.security.MyUserPrincipal;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class CurrencyExchangeService {

    private final ServiceUserDeposit serviceUserDeposit;
    private final SendCurrencyResponse sendCurrencyResponse;
    private final ServiceCurrencyExchange serviceCurrencyExchange;

    public CurrencyExchangeService(ServiceUserDeposit serviceUserDeposit, SendCurrencyResponse sendCurrencyResponse, ServiceCurrencyExchange serviceCurrencyExchange) {
        this.serviceUserDeposit = serviceUserDeposit;
        this.sendCurrencyResponse = sendCurrencyResponse;
        this.serviceCurrencyExchange = serviceCurrencyExchange;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit allDeposit() {
        final var result = serviceUserDeposit.getById(userPrincipal().getUserId());
        if (result.isEmpty()) throw new RuntimeException("У Вас нет депозитов.");
        return result.get();
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit addDeposit(BigDecimal summa) {
        var user = userPrincipal();
        final var result = serviceUserDeposit.getById(user.getUserId());
        if (result.isEmpty()) {
            UserDeposit newUserDeposit = new UserDeposit() {{
                setUserId(user.getUserId());
                setDeposit(summa);
            }};
            serviceUserDeposit.insert(newUserDeposit);
            return newUserDeposit;
        }
        var userDeposit = result.get();
        userDeposit.setDeposit(result.get().getDeposit().add(summa));
        serviceUserDeposit.save(userDeposit);
        return userDeposit;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit subtractDeposit(BigDecimal summa) {
        final var result = serviceUserDeposit.getById(userPrincipal().getUserId());
        if (result.isEmpty()) throw new RuntimeException("У Вас нет депозита.");
        var userDeposit = result.get();
        if (userDeposit.getDeposit().compareTo(summa) < 0)
            throw new RuntimeException("У Вас на депозите нет столько средств.");
        else userDeposit.setDeposit(userDeposit.getDeposit().subtract(summa));
        serviceUserDeposit.save(userDeposit);
        return userDeposit;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit currencyExchange(RequestType rdt) {
        if (null == rdt) throw new RuntimeException("У Вас нет входных данных.");
        final var result = serviceUserDeposit.getById(userPrincipal().getUserId());
        if (result.isEmpty()) throw new RuntimeException("У Вас нет депозита.");
        var userDeposit = result.get();
        final var currencyCbr = sendCurrencyResponse.send();
        if (null == currencyCbr) throw new RuntimeException("Сбой программы загрузки валют.");
        if ("RUR".equals(rdt.typeFrom.toUpperCase())) {
            /* из  рублей тоесть покупаем валюту  */
            return buyCurrency(currencyCbr, userDeposit, rdt);
        } else
            /* в рубли тоесть продаем валюту */ {
            return sellCurrency(currencyCbr, userDeposit, rdt);
        }
    }

    /**
     * Метод покупки валюты
     *
     * @param currencyCbr Валютный депозит.
     * @param userDeposit Основной рублевый депозит.
     * @param rdt         заказ пользователя на покупку валюты.
     * @return Возвращаем измененный основной рублевый депозит.
     */
    private UserDeposit buyCurrency(CurrencyRateContainer currencyCbr, UserDeposit userDeposit, RequestType rdt) {
        /* из  рублей тоесть покупаем валюту  */
        var currencyTo = currencyCbr.getCurrency().stream()
                .filter(currencyRate -> currencyRate.getType().equals(rdt.typeTo))
                .collect(Collectors.toList());
        if (currencyTo.size() != 1)
            throw new IllegalStateException("Ошибка выгрузки валюты. В запросе пусто или больше 1 элемента = " + currencyTo);
        var rurOrder = currencyTo.get(0).value.multiply(BigDecimal.valueOf(rdt.amount));
        if (userDeposit.getDeposit().compareTo(rurOrder) < 0)
            throw new RuntimeException("Недостаточно средств на депозите.");
        /* вычетаем рублевый депозит*/
        var tempDeposit = userDeposit;
        tempDeposit.setDeposit(tempDeposit.getDeposit().subtract(rurOrder));
        serviceUserDeposit.save(tempDeposit);
        /* добавляем валютные депозит */
        var filterUser = userDeposit.getCurrencyExchanges().stream()
                .filter(currencyExchange -> currencyExchange.getCurrencyType().equals(rdt.typeTo))
                .collect(Collectors.toList());
        if (filterUser.size() > 0) {
            /* У клиента есть данная валюта добавляем к валютному депозиту */
            var oldCurrencyDeposit = filterUser.get(0);
            oldCurrencyDeposit.setCurrencyDeposit(oldCurrencyDeposit.getCurrencyDeposit().add(BigDecimal.valueOf(rdt.amount)));
            serviceCurrencyExchange.save(oldCurrencyDeposit);

        } else {
            /* У клиента нет данной валюты в депозите создаем новую и добавляем */
            var newCurrencyDeposit = new CurrencyExchange() {{
                setCurrencyDeposit(BigDecimal.valueOf(rdt.amount));
                setCurrencyType(rdt.typeTo);
                setUserDeposit(userDeposit.getUserId());
            }};
            tempDeposit.getCurrencyExchanges().add(serviceCurrencyExchange.insert(newCurrencyDeposit));
        }
        return tempDeposit;
    }

    /**
     * Метод продажи валюты
     *
     * @param currencyCbr Валютный депозит.
     * @param userDeposit Основной рублевый депозит.
     * @param rdt         заказ пользователя на покупку валюты.
     * @return Возвращаем измененный основной рублевый депозит.
     */
    private UserDeposit sellCurrency(CurrencyRateContainer currencyCbr, UserDeposit userDeposit, RequestType rdt) {
        /* из  валюты тоесть продаем и добавляем на депозит */
        var currencyFrom = currencyCbr.getCurrency().stream()
                .filter(currencyRate -> currencyRate.getType().equals(rdt.typeFrom))
                .collect(Collectors.toList());
        if (currencyFrom.size() != 1)
            throw new IllegalStateException("Ошибка выгрузки валюты. В запросе пусто или больше 1 элемента = " + currencyFrom);
        var filterUser = userDeposit.getCurrencyExchanges().stream()
                .filter(currencyExchange -> currencyExchange.getCurrencyType().equals(rdt.typeFrom))
                .collect(Collectors.toList());
        if (null == filterUser || filterUser.size() == 0)
            throw new RuntimeException("Нет данного валютного депозита. Не верно ввели данные");
        if (filterUser.get(0).getCurrencyDeposit().compareTo(BigDecimal.valueOf(rdt.amount)) == -1)
            throw new RuntimeException("Введена сумма превышающая валютный депозит.");

        /*чего не делали с депозитом надо добавить на рублевый депозит */
        var rurOrder = currencyFrom.get(0).value.multiply(BigDecimal.valueOf(rdt.amount));
        var tempDeposit = userDeposit;
        tempDeposit.setDeposit(tempDeposit.getDeposit().add(rurOrder));
        serviceUserDeposit.save(tempDeposit);

        if (filterUser.get(0).getCurrencyDeposit().compareTo(BigDecimal.valueOf(rdt.amount)) > 0) {
            /*вычетаем и оставляем депозит*/
            var oldCurrencyDeposit = filterUser.get(0);
            oldCurrencyDeposit.setCurrencyDeposit(oldCurrencyDeposit.getCurrencyDeposit().subtract(BigDecimal.valueOf(rdt.amount)));
            serviceCurrencyExchange.save(oldCurrencyDeposit);
        } else {
            /*вычетаем и закрываем депозит*/
            var oldCurrencyDeposit = filterUser.get(0);
            oldCurrencyDeposit.setCurrencyDeposit(oldCurrencyDeposit.getCurrencyDeposit().subtract(BigDecimal.valueOf(rdt.amount)));
            serviceCurrencyExchange.delete(oldCurrencyDeposit);
        }
        return tempDeposit;
    }

    private MyUserPrincipal userPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        final var userPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if (null == userPrincipal) throw new RuntimeException("Пользователь не авторизован.");
        return userPrincipal;
    }

}
