package ru.otus.spring.security.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        final var name = SecurityContextHolder.getContext().getAuthentication().getName();
        switch (name) {
            case "anonymousUser":
                return Health.down()
                        .status(Status.DOWN)
                        .withDetail("message", "Все, нас взломали!")
                        .build();
            case "user":
            case "admin":
                return Health.up().withDetail("message", "Добро пожаловать все работает в штатном порядке.").build();
            case "staff":
                return Health.up().withDetail("message", "Данные метрики для Вас не доступны.").build();
            default:
                return Health.down()
                        .status(Status.DOWN)
                        .withDetail("message", "Неизвестный пользователь !!!!! ")
                        .build();
        }
    }
}
