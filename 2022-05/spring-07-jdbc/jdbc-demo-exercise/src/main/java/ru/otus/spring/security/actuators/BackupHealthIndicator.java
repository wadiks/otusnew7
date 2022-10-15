package ru.otus.spring.security.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.BooksDao;

@Component
public class BackupHealthIndicator implements HealthIndicator {
    private final BooksDao booksDao;

    public BackupHealthIndicator(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @Override
    public Health health() {

        var countSize = booksDao.count();

        if (countSize > 1000)
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "В база данных находиться больше 1000 книг. Необходимо делать Backup базы данных.")
                    .build();
        else
            return Health
                    .up()
                    .withDetail("message", "База данных меньше 1000 книг.")
                    .build();

    }
}
