package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.service.CardGeneratorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CardGeneratorServiceImpl implements CardGeneratorService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-yy");

    @Override
    public String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        // Generar 16 dígitos
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);  // Generar un número entre 0 y 9
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }

    @Override
    public String generatePin() {
        Random random = new Random();
        int pin = random.nextInt(9000) + 1000;  // Generar un número entre 1000 y 9999
        return String.valueOf(pin);
    }

    @Override
    public String generateIssueDate() {
        LocalDate issueDate = LocalDate.now();  // Fecha actual
        return issueDate.format(DATE_FORMATTER);  // Formatear la fecha como String
    }

    @Override
    public String generateExpirationDate() {
        LocalDate expirationDate = LocalDate.now().plusYears(5);  // Sumar 5 años
        return expirationDate.format(DATE_FORMATTER);  // Formatear la fecha como String
    }
}
