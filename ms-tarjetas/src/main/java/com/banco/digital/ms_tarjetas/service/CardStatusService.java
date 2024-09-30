package com.banco.digital.ms_tarjetas.service;

import com.banco.digital.ms_tarjetas.model.CardStatus;

import java.util.Optional;

public interface CardStatusService extends BaseService<CardStatus, Integer> {
    Optional<CardStatus> findByDetail(String detail);
}
