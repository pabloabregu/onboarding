package com.banco.digital.ms_tarjetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "resumen_emitidos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssuedSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resumeMongoID", nullable = false, length = 45)
    private String resumeMongoID;

    @Column(name = "total_pesos", nullable = false, precision = 10, scale = 0)
    private BigDecimal totalPesos;

    @Column(name = "total_dollars", nullable = false, precision = 10, scale = 0)
    private BigDecimal totalDollars;

    @Column(name = "due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate;
}
