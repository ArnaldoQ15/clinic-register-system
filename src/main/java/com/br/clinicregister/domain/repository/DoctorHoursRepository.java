package com.br.clinicregister.domain.repository;

import com.br.clinicregister.model.PersonDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHoursRepository extends JpaRepository<PersonDoctor, Long> {
}
