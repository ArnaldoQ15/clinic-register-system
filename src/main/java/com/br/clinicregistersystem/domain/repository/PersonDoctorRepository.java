package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDoctorRepository extends JpaRepository<PersonDoctor, Long> {

    Optional<PersonDoctor> findByPersonEmail(@Param("personEmail") String personEmail);
    Optional<PersonDoctor> findByPersonDocumentCpf(@Param("personDocumentCpf") String personDocumentCpf);
    PersonDoctor findByMedicalEspeciality (@Param("medicalEspeciality") MedicalEspeciality medicalEspeciality);

}
