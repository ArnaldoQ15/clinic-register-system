package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Doctor;
import com.br.clinicregistersystem.model.DoctorEspeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByPersonEmail(@Param("personEmail") String personEmail);
    Optional<Doctor> findByPersonDocumentCpf(@Param("personDocumentCpf") String personDocumentCpf);
    Doctor findByDoctorEspeciality (@Param("doctorEspeciality") DoctorEspeciality doctorEspeciality);

}
