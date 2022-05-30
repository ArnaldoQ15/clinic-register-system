package com.br.clinicregister.domain.repository;

import com.br.clinicregister.model.PersonDoctorAgendaAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHoursRepository extends JpaRepository<PersonDoctorAgendaAppointments, Long> {
}
