package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {

    /**
     * ид записси
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ид клиента
     */
    @Column(name = "user_deposit")
    private Long userDeposit;

    /**
     * тип валюты
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * Валютный депозит (Количество купленной валюты)
     */
    @Column(name = "currency_deposit")
    private BigDecimal currencyDeposit;
}
