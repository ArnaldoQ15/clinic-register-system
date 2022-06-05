package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorEspeciality;
import com.br.clinicregistersystem.model.DoctorHourSaturday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourSaturdayRepository extends JpaRepository<DoctorHourSaturday, Long> {

    DoctorHourSaturday findByDoctorEspeciality (@Param("doctorEspeciality") DoctorEspeciality doctorEspeciality);

}