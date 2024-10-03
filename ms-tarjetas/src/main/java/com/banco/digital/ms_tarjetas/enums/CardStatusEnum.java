package com.banco.digital.ms_tarjetas.enums;

public enum CardStatusEnum {
    ACTIVE("Activa");

    private final String type;

    CardStatusEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
