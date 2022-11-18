package ru.otus.spring.domain;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.otus.spring.Utils;
import ru.otus.spring.controller.RequestSecurity;
import ru.otus.spring.dao.service.ServiceSecurity;
import ru.otus.spring.model.Security;

@Service
public class SecurityService {

    private final ServiceSecurity serviceSecurity;


    public SecurityService(ServiceSecurity serviceSecurity) {
        this.serviceSecurity = serviceSecurity;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    public Security addUser(RequestSecurity sec) {
        Security security = new Security() {{
            setUserSecurity(sec.getLogin());
            setPasswordSecurity(sec.getPassword());
            setRoleSecurity(sec.getRole());
        }};
        security = serviceSecurity.insert(security);
        security.setPasswordSecurity(Utils.maskString(security.getPasswordSecurity()));
        return security;
    }

}
