package ru.otus.spring.domain;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.controller.RequestDataType;
import ru.otus.spring.dao.service.ServiceUserDeposit;
import ru.otus.spring.model.UserDeposit;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeService {

    private final ServiceUserDeposit serviceUserDeposit;
    private final SendCurrencyResponse sendCurrencyResponse;

    public CurrencyExchangeService(ServiceUserDeposit serviceUserDeposit, SendCurrencyResponse sendCurrencyResponse) {
        this.serviceUserDeposit = serviceUserDeposit;
        this.sendCurrencyResponse = sendCurrencyResponse;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit allDeposit() {
        final var rezult = userDeposit();
        System.out.println("rezult = " + rezult);
        return rezult;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit addDeposit(BigDecimal summa) {
        var rezult = userDeposit();
        if (null == rezult)
            rezult = new UserDeposit() {{
                setUserId(1L);
                setDeposit(summa);
            }};
        else rezult.setDeposit(rezult.getDeposit().add(summa));
        System.out.println("rezult = " + rezult);
        serviceUserDeposit.save(rezult);
        return rezult;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit subtractDeposit(BigDecimal summa) {
        var rezult = userDeposit();
        if (null == rezult) throw new RuntimeException("У Вас нет депозита.");

        if (rezult.getDeposit().compareTo(summa) < 0)
            throw new RuntimeException("У Вас на депозите нет столько средств.");
        else rezult.setDeposit(rezult.getDeposit().subtract(summa));

        System.out.println("rezult = " + rezult);
        serviceUserDeposit.save(rezult);
        return rezult;
    }

    @PreAuthorize("hasAnyRole('USER')")
    public UserDeposit currencyExchange(RequestDataType rdt) {
        var rezult = userDeposit();
        if (null == rezult) throw new RuntimeException("У Вас нет депозита.");


         final var  currencyCbr = sendCurrencyResponse.send();

      /*  rdt.typeFrom


        if (rezult.getDeposit().compareTo(summa) < 0)
            throw new RuntimeException("У Вас на депозите нет столько средств.");
        else rezult.setDeposit(rezult.getDeposit().subtract(summa));

        System.out.println("rezult = " + rezult);
        serviceUserDeposit.save(rezult);

       */
        return rezult;
    }

    private UserDeposit userDeposit() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        final var rezsec = authentication.getPrincipal();
        System.out.println("edit==" + authentication.getPrincipal());
        if (null == rezsec) throw new RuntimeException("Пользователь не авторизован.");
        return serviceUserDeposit.getById(1).get();
    }




/*
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
        System.out.println("edit==" + authentication.getPrincipal());

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    List<Books> findAll();

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    Optional<Books> findById(Long id);

    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    Books save(Books entity);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(Books entity);
}
*/
}
