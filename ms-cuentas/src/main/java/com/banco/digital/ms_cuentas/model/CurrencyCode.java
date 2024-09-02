package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "codigo_moneda")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_moneda", nullable = false)
    private Integer code;

    @Column(name = "pais", nullable = false)
    private String country;

    @Column(name = "simbolo", nullable = false)
    private String symbol;
}
