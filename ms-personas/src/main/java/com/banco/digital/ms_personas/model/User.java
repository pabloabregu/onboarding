package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "persnum")
    private Long idUser;

    @Getter
    @Setter
    @Column(name = "nombre")
    private String firstname;

    @Getter
    @Setter
    @Column(name = "apellido")
    private String lastname;

    @Getter
    @Setter
    @Column(name = "dni")
    private String dni;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "estado")
    private UserState state;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "tipo")
    private UserType type;
}
