package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "doctorAppointments")
public class PersonDoctorAgendaAppointments {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    private PersonDoctorAgenda personDoctorAgenda;

}
