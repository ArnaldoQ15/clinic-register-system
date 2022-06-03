package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.DoctorHourMonday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHourMondayRepository extends JpaRepository<DoctorHourMonday, Long> {
}