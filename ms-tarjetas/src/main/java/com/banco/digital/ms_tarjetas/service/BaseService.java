package com.banco.digital.ms_tarjetas.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, I> {
    List<T> findAll();
    Optional<T> findById(I id);
}
