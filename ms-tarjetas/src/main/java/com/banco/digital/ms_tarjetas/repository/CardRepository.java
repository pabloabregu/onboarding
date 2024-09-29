package com.banco.digital.ms_tarjetas.repository;

import com.banco.digital.ms_tarjetas.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
}
