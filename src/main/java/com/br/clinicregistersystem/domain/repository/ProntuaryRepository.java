package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientProntuary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuaryRepository extends JpaRepository<PersonPacientProntuary, Long> {
}
