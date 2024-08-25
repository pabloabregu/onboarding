package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado_usuario")
    private Long idUserState;

    @Column(name = "descripcion")
    private String description;
}
