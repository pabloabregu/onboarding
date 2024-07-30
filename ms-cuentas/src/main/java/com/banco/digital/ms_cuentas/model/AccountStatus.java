package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "estado_cuenta")
@ToString
public class AccountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private int id;

    @Getter
    @Setter
    @Column(name = "detalle", nullable = false)
    private String detail;
}
