package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorHourTuesday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourTuesdayRepository extends JpaRepository<DoctorHourTuesday, Long> {
}