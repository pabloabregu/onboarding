package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.repository.AddressRepository;
import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address generateAddress(UserRequest userRequest, User user) {
        Address address = Address.builder()
                .idUser(user.getIdUser())
                .street(userRequest.getStreet())
                .number(userRequest.getNumber())
                .province(userRequest.getProvince())
                .locality(userRequest.getLocality())
                .build();
        return addressRepository.save(address);
    }
}
