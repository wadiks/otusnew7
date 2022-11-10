package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_security")
public class Security {
    /**
     * ид пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * логин пользователя
     */
    @Column(name = "user_security")
    private String userSecurity;

    /**
     * Пароль пользователя
     */
    @Column(name = "password_security")
    private String passwordSecurity;

    /**
     * Роль пользователя
     */
    @Column(name = "role_security")
    private String roleSecurity;
}
