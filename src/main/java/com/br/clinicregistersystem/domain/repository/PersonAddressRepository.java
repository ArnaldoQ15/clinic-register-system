package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {

    @Query(value = "SELECT * FROM person_address pa " +
            "JOIN person p ON p.person_id = pa.person_id " +
            "WHERE pa.person_id = :personId", nativeQuery = true)
    List<PersonAddress> findByPersonId(@Param("personId") Long personId);

    @Query(value = "SELECT * FROM person_address pa " +
            "JOIN person p ON p.person_id = pa.person_id " +
            "WHERE pa.person_id = :personId AND pa.address_id = :addressId", nativeQuery = true)
    Optional<PersonAddress> findByPersonIdAndAddressId(Long personId, Long addressId);

}
