package ru.otus.spring.sourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder {

    private static ApplicationContext ctx;

    @Autowired
    public ApplicationContextHolder(ApplicationContext applicationContext) {
        ApplicationContextHolder.ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}

