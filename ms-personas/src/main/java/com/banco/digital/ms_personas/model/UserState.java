package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_usuario")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "idestado_usuario")
    private Long idUserState;

    @Getter
    @Setter
    @Column(name = "descripcion")
    private String description;
}
