package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "numcue", nullable = false)
    private Long accountNumber;

    @Getter
    @Setter
    @Column(name = "persnum", nullable = false)
    private Long personNumber;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "divisa")
    private CurrencyCode currency;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "estado")
    private AccountStatus status;

    @Getter
    @Setter
    @Column(name = "saldo", nullable = false, precision = 10, scale = 0)
    private BigDecimal salary;
}
