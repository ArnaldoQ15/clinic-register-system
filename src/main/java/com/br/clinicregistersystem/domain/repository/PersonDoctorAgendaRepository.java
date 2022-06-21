package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonDoctorAgenda;
import com.br.clinicregistersystem.util.enums.DayWeek;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonDoctorAgendaRepository extends JpaRepository<PersonDoctorAgenda, Long> {

    @Query(value = "SELECT * FROM person_doctor_agenda pda " +
            "JOIN person_doctor pd on pda.person_id = pd.person_id", nativeQuery = true)
    List<PersonDoctorAgenda> findByPersonId(Long personId);

    Optional<PersonDoctorAgenda> findByMedicalEspecialityAndDayWeek(MedicalEspeciality medicalEspeciality, DayWeek dayWeek);

}