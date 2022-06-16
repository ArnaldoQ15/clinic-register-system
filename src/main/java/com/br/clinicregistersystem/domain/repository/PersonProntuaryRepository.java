package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientProntuary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonProntuaryRepository extends JpaRepository<PersonPacientProntuary, Long> {

    List<PersonPacientProntuary> findByPersonId(Long personId);

}
