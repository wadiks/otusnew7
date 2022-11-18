package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Контейнер обмена пользователей")
public class RequestSecurity {

    @Schema(description = "Пароль пользователя")
    private String password;

    @Schema(description = "Логин пользователя")
    private String login;

    @Schema(description = "Роль пользователя")
    private String role;

}
