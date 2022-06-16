package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDoctorRepository extends JpaRepository<PersonDoctor, Long> {

    Optional<PersonDoctor> findByPersonEmail(String personEmail);
    Optional<PersonDoctor> findByPersonDocumentCpf(String personDocumentCpf);
    Optional<PersonDoctor> findByMedicalEspeciality (MedicalEspeciality medicalEspeciality);

    Boolean existsByPersonEmail (String personEmail);

}
