package com.banco.digital.ms_personas.enums;

public enum UserStateEnum {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    SUSPENDIDO("Suspendido"),
    BLOQUEADO("Bloqueado"),
    CANCELADO("Cancelado");
    private final String type;

    UserStateEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
