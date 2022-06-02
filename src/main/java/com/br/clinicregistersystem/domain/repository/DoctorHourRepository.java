package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourRepository extends JpaRepository<DoctorHour, Long> {
}