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
    private Integer idAddress;

    @Column(name = "persnum")
    private Integer idUser;

    @Column(name = "calle")
    private String street;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "provincia")
    private String province;

    @Column(name = "localidad")
    private String locality;
}
