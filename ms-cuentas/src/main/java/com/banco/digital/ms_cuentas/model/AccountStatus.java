package com.banco.digital.ms_cuentas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_cuenta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "detalle", nullable = false)
    private String detail;
}
