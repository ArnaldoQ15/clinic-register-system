package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonPacientChildRepository extends JpaRepository<PersonPacientChild, Long> {

    List<PersonPacientChild> findByPersonId(Long personId);

}
