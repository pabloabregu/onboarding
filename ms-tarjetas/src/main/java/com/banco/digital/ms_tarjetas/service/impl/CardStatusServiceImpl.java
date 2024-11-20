package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.CardStatus;
import com.banco.digital.ms_tarjetas.repository.CardStatusRepository;
import com.banco.digital.ms_tarjetas.service.CardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardStatusServiceImpl implements CardStatusService {

    private final CardStatusRepository cardStatusRepository;

    @Autowired
    public CardStatusServiceImpl(CardStatusRepository cardStatusRepository) {
        this.cardStatusRepository = cardStatusRepository;
    }

    @Override
    public List<CardStatus> findAll() {
        return cardStatusRepository.findAll();
    }

    @Override
    public Optional<CardStatus> findById(Integer id) {
        return cardStatusRepository.findById(id);
    }

    @Override
    public Optional<CardStatus> findByDetail(String detail) {
        return cardStatusRepository.findByDetail(detail);
    }
}
