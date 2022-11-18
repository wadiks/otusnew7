package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange implements Serializable {

    /**
     * ид записси
     */
    @SequenceGenerator(name = "cur_seq",
            sequenceName = "currency_sequence",
            initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cur_seq")
    @Id
    @Column(name = "id")
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
