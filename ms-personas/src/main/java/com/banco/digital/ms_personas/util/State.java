package com.banco.digital.ms_personas.util;

public class State {

    private State() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static final String ACTIVO = "Activo";
    public static final String INACTIVO = "Inactivo";
    public static final String SUSPENDIDO = "Suspendido";
    public static final String BLOQUEADO = "Bloqueado";
    public static final String CANCELADO = "Cancelado";
}
