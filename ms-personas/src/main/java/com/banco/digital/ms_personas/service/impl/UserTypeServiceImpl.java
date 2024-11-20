package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.UserType;
import com.banco.digital.ms_personas.repository.UserTypeRepository;
import com.banco.digital.ms_personas.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public Optional<UserType> findById(Integer id) {
        return userTypeRepository.findById(id);
    }

    @Override
    public UserType findByDescription(String description) {
        return userTypeRepository.findByDescription(description);
    }
}
