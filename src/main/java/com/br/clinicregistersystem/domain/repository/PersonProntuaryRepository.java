package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPacientProntuary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonProntuaryRepository extends JpaRepository<PersonPacientProntuary, Long> {

    @Query(value = "SELECT * FROM person_pacient_prontuary ppp " +
            "INNER JOIN person_pacient pp ON pp.person_id = ppp.person_id " +
            "WHERE ppp.person_id = :personId", nativeQuery = true)
    List<PersonPacientProntuary> findByPersonId(@Param("personId") Long personId);

}
