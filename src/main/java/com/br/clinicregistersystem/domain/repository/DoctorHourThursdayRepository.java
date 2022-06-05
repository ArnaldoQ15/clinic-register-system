package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorEspeciality;
import com.br.clinicregistersystem.model.DoctorHourThursday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourThursdayRepository extends JpaRepository<DoctorHourThursday, Long> {

    DoctorHourThursday findByDoctorEspeciality (@Param("doctorEspeciality") DoctorEspeciality doctorEspeciality);

}