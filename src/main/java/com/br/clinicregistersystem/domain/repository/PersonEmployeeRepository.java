package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonEmployeeRepository extends JpaRepository<PersonEmployee, Long> {

    Boolean existsByInstitucionalEmail(String institucionalEmail);

}