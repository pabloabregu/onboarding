package com.banco.digital.ms_personas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persnum")
    private Long idUser;

    @Column(name = "nombre")
    private String firstname;

    @Column(name = "apellido")
    private String lastname;

    @Column(name = "dni")
    private String dni;

    @ManyToOne
    @JoinColumn(name = "estado")
    @ToString.Exclude
    private UserState state;

    @ManyToOne
    @JoinColumn(name = "tipo")
    @ToString.Exclude
    private UserType type;
}
