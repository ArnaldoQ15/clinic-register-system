package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import static com.br.clinicregister.model.PersonDoctorAgendaWeekDays.*;

@Getter
@Setter
@Entity(name = "doctorAgenda")
public class PersonDoctorAgenda extends PersonDoctor {


    @NotNull
    @Id
    @Column(name = "agenda_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agendaId;

    @Column(name = "agenda_appointments")
    private PersonDoctorAgendaAppointments appointments;


}
