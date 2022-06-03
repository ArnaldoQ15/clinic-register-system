package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorHourSaturday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourSaturdayRepository extends JpaRepository<DoctorHourSaturday, Long> {
}