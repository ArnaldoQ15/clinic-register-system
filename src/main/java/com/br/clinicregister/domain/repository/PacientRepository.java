package com.br.clinicregister.domain.repository;

import com.br.clinicregister.model.PersonPacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<PersonPacient, Long> {
}
