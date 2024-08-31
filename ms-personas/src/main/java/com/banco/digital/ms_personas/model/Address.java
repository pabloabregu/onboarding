package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domicilio")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddomicilio")
    private Long idAddress;

    @Column(name = "persnum")
    private Long idUser;

    @Column(name = "calle")
    private String street;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "provincia")
    private String province;

    @Column(name = "localidad")
    private String locality;
}
