package com.banco.digital.ms_tarjetas.enums;

public enum TypeCard {
    CREDIT("C");

    private final String type;

    TypeCard(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
