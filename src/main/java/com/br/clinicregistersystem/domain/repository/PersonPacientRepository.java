package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonPacientRepository extends JpaRepository<PersonPacient, Long> {
}
