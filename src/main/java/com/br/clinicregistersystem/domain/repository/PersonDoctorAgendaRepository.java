package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonDoctorAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDoctorAgendaRepository extends JpaRepository<PersonDoctorAgenda, Long> {

    @Query(value = "SELECT * FROM person_doctor_agenda pda " +
            "JOIN person_doctor pd on pda.person_id = pd.person_id", nativeQuery = true)
    List<PersonDoctorAgenda> findByPersonId(Long personId);

}