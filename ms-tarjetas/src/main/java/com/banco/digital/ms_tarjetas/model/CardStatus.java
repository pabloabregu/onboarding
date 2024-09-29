package com.banco.digital.ms_tarjetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado_tarjeta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "detalle", nullable = false, length = 45)
    private String detail;
}
