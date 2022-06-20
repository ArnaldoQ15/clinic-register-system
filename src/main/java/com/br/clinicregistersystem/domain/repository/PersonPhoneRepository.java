package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonPhoneRepository extends JpaRepository<PersonPhone, Long> {

    @Query(value = "SELECT * FROM person_phone pp " +
            "JOIN person p ON p.person_id = pp.person_id " +
            "WHERE p.person_id = :personId", nativeQuery = true)
    List<PersonPhone> findByPersonId(@Param("personId") Long personId);

    @Query(value = "SELECT * FROM person_phone pp " +
            "JOIN person p ON p.person_id = pp.person_id " +
            "WHERE p.person_id = :personId AND pp.phone_id = :phoneId", nativeQuery = true)
    Optional<PersonPhone> findByPersonIdAndPhoneId(Long personId, Long phoneId);

}
