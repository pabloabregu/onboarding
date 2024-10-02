package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.request.IssueCardRequest;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import org.springframework.stereotype.Service;

@Service
public class CardEventProcessorServiceImpl implements CardEventProcessorService {
    @Override
    public Card processCardCreation(IssueCardRequest request) {
        return null;
    }
}
