package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonPacientChildRepository extends JpaRepository<PersonPacientChild, Long> {

    @Query(value = "SELECT * FROM person_pacient_child ppc " +
            "INNER JOIN person_pacient pp ON pp.person_id = ppc.person_id " +
            "WHERE ppc.person_id = :personId", nativeQuery = true)
    List<PersonPacientChild> findByPersonId(@Param("personId") Long personId);

}