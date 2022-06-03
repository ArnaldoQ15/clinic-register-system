package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Doctor;
import com.br.clinicregistersystem.model.DoctorEspeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<DoctorEspeciality> findByDoctorEspeciality (@Param("doctorEspeciality") String doctorEspeciality);

}
