package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
}
