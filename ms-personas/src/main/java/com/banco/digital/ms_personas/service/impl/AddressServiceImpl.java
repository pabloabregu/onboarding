package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.repository.AddressRepository;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address generateAddress(UserRegisterRequest userRegisterRequest, User user) {
        Address address = Address.builder()
                .idUser(user.getIdUser())
                .street(userRegisterRequest.getStreet())
                .number(userRegisterRequest.getNumber())
                .province(userRegisterRequest.getProvince())
                .locality(userRegisterRequest.getLocality())
                .build();
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }
}
