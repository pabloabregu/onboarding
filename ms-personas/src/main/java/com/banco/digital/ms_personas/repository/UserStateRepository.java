package com.banco.digital.ms_personas.repository;

import com.banco.digital.ms_personas.model.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStateRepository extends JpaRepository<UserState, Long> {
    UserState findByDescription(String string);
}
