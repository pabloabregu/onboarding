package com.banco.digital.ms_personas.util;

public class State {

    private State() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static final String ACTIVO = "ACTIVO";
    public static final String INACTIVO = "INACTIVO";
    public static final String BLOQUEADO = "BLOQUEADO";
    public static final String CANCELADO = "CANCELADO";
    public static final String SUSPENDIDO = "SUSPENDIDO";
}
