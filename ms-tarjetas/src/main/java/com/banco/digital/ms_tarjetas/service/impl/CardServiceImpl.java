package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.repository.CardRepository;
import com.banco.digital.ms_tarjetas.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(String id) {
        return cardRepository.findById(id);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}