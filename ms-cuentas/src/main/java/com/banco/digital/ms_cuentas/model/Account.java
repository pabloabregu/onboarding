package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "numcue", nullable = false)
    private String accountNumber;

    @Column(name = "persnum", nullable = false)
    private Long personNumber;

    @ManyToOne
    @JoinColumn(name = "divisa")
    private CurrencyCode currency;

    @ManyToOne
    @JoinColumn(name = "estado")
    private AccountStatus status;

    @Column(name = "saldo", nullable = false, precision = 10)
    private BigDecimal salary;
}
