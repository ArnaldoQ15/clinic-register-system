package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {

    List<Pacient> findByPersonEmail(@Param("personEmail") String personEmail);
    List<Pacient> findByPersonDocumentCpf(@Param("personDocumentCpf") Long personDocumentCpf);

}
