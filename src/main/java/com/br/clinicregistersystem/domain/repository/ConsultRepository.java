package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
}
