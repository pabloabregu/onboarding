package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domicilio")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "iddomicilio")
    private Long idAddress;

    @Getter
    @Setter
    @Column(name = "persnum")
    private Long idUser;

    @Getter
    @Setter
    @Column(name = "calle")
    private String street;

    @Getter
    @Setter
    @Column(name = "numero")
    private Integer number;

    @Getter
    @Setter
    @Column(name = "provincia")
    private String province;

    @Getter
    @Setter
    @Column(name = "localidad")
    private String locality;
}
