package com.banco.digital.ms_tarjetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjetas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "numtarj", length = 30, nullable = false)
    private Integer cardNumber;

    @Column(name = "numcue", nullable = false)
    private Integer accountNumber;

    @Column(name = "f_vencimiento", length = 10, nullable = false)
    private String expirationDate;

    @Column(name = "pin", nullable = false)
    private Integer pin;

    @ManyToOne
    @JoinColumn(name = "estado", nullable = false)
    private CardStatus cardStatus;

    @Column(name = "f_emision", length = 10, nullable = false)
    private String issueDate;

    @Column(name = "tipo", length = 1, nullable = false)
    private String type;
}
