package com.banco.digital.ms_personas.repository;

import com.banco.digital.ms_personas.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByDescription(String string);
}
