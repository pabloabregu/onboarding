package com.banco.digital.ms_tarjetas.service;

public interface CardGeneratorService {
    String generateCardNumber();

    String generatePin();

    String generateIssueDate();

    String generateExpirationDate();
}
