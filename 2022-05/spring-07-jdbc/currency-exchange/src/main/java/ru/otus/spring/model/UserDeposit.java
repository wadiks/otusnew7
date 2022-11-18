package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_deposit")
public class UserDeposit implements Serializable {

    /**
     * ид пользователя
     */

    @Id
    @Column(name = "user_id")
    private Long userId;
    /**
     * Основной рублевый депозит пользователя
     */
    private BigDecimal deposit;

    /**
     * Валютный депозит
     */
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "user_deposit")
    @OneToMany(targetEntity = CurrencyExchange.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CurrencyExchange> currencyExchanges;

}
