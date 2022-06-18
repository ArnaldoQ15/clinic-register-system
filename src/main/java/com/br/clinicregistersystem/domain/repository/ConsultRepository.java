package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    @Query(value = "SELECT * FROM consult " +
            "JOIN person_pacient pp ON consult.person_pacient_person_id = pp.person_id " +
            "WHERE consult.person_pacient_person_id = :personId", nativeQuery = true)
    List<Consult> findByPersonId(@Param("personId") Long personId);

}
