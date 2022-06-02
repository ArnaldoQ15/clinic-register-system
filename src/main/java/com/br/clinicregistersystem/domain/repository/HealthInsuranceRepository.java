package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PacientHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthInsuranceRepository extends JpaRepository<PacientHealthInsurance, Long> {
}
