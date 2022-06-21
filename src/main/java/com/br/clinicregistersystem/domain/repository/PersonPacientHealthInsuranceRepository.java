package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonPacientHealthInsuranceRepository extends JpaRepository<PersonPacientHealthInsurance, Long> {

    @Query(value = "SELECT * FROM person_pacient_health_insurance pphi " +
            "INNER JOIN person_pacient pp on pphi.health_insurance_id = pp.health_insurance_id " +
            "WHERE pp.person_id = :personId", nativeQuery = true)
    Optional<PersonPacientHealthInsurance> findByPersonId(Long personId);

}