package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "codigo_moneda")
@ToString
public class CurrencyCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "cod_moneda", nullable = false)
    private Long currencyCode;

    @Getter
    @Setter
    @Column(name = "pais", nullable = false)
    private String country;

    @Getter
    @Setter
    @Column(name = "simbolo", nullable = false)
    private String symbol;
}
