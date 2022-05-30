package com.br.clinicregister.domain.repository;

import com.br.clinicregister.model.DoctorCardiology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialityCardiologyRepository extends JpaRepository<DoctorCardiology, Long> {
}
