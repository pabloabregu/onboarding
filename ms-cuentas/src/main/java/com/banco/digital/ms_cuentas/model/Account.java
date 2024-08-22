package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Getter
    @Setter
    @Column(name = "numcue", nullable = false)
    private String accountNumber;

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
    @Column(name = "saldo", nullable = false, precision = 10)
    private BigDecimal salary;
}
