package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctorHourMonday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDoctorHourMondayRepository extends JpaRepository<PersonDoctorHourMonday, Long> {

    PersonDoctorHourMonday findByMedicalEspeciality (@Param("medicalEspeciality") MedicalEspeciality medicalEspeciality);

}