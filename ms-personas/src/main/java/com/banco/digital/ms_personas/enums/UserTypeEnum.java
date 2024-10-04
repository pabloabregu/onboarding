package com.banco.digital.ms_personas.enums;

public enum UserTypeEnum {
    CLIENTE("Cliente"),
    EMPLEADO("Empleado"),
    ADMINISTRADOR("Administrador"),
    PROVEEDOR("Proveedor"),
    INVITADO("Invitado");

    private final String type;

    UserTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
