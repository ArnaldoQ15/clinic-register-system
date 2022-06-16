package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonPacientHealthInsuranceRepository extends JpaRepository<PersonPacientHealthInsurance, Long> {

    Optional<PersonPacientHealthInsurance> findByPersonId(Long personId);

}
