package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.UserState;
import com.banco.digital.ms_personas.repository.UserStateRepository;
import com.banco.digital.ms_personas.service.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStateServiceImpl implements UserStateService {

    @Autowired
    private UserStateRepository userStateRepository;

    @Override
    public List<UserState> findAll() {
        return userStateRepository.findAll();
    }

    @Override
    public Optional<UserState> findById(Long id) {
        return userStateRepository.findById(id);
    }

    @Override
    public UserState findByDescription(String string) {
        return userStateRepository.findByDescription(string);
    }
}
