package com.banco.digital.ms_tarjetas.enums;

public enum TypeCardEnum {
    CREDIT("C"),
    DEBIT("D");

    private final String type;

    TypeCardEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
