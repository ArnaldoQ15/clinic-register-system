package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "doctorAppointments")
public class PersonDoctorAgendaAppointments {

    @NotNull
    @Id
    @Column(name = "appointment_id")
    private Long appointmentId;

    @ManyToOne
    private PersonDoctorAgenda personDoctorAgenda;



}
