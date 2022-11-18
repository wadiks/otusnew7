package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_security")
public class Security implements Serializable {
    /**
     * ид пользователя
     */
    @SequenceGenerator(name = "sec_seq",
            sequenceName = "security_sequence",
            initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_seq")
    @Id
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
