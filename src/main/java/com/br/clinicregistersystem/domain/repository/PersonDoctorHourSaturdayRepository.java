package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctorHourSaturday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDoctorHourSaturdayRepository extends JpaRepository<PersonDoctorHourSaturday, Long> {

    PersonDoctorHourSaturday findByMedicalEspeciality (@Param("medicalEspeciality") MedicalEspeciality medicalEspeciality);

}