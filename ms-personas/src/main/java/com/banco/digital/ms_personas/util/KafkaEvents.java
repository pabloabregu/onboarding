package com.banco.digital.ms_personas.util;

public class KafkaEvents {

    private KafkaEvents() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }
    public static final String CREATE_USER = "new-user";
}
