package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctorHourThursday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDoctorHourThursdayRepository extends JpaRepository<PersonDoctorHourThursday, Long> {

    PersonDoctorHourThursday findByMedicalEspeciality (@Param("medicalEspeciality") MedicalEspeciality medicalEspeciality);

}