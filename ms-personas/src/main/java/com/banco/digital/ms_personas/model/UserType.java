package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_usuario")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "idtipo_usuario")
    private Long idType;

    @Getter
    @Setter
    @Column(name = "descripcion")
    private String description;
}
