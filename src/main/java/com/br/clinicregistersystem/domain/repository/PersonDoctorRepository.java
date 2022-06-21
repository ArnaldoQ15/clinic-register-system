package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDoctorRepository extends JpaRepository<PersonDoctor, Long> {

    Optional<PersonDoctor> findByMedicalEspeciality (MedicalEspeciality medicalEspeciality);

}
