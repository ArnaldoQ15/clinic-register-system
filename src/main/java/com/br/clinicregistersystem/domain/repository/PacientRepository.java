package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {

    Optional<Pacient> findByPersonEmail(@Param("personEmail") String personEmail);
    Optional<Pacient> findByPersonDocumentCpf(@Param("personDocumentCpf") String personDocumentCpf);

}
