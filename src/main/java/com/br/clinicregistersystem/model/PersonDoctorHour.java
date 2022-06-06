package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class PersonDoctorHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorAgendaId;

    @ManyToOne
    private PersonDoctor personDoctor;

    @OneToOne
    private PersonDoctorHourMonday personDoctorHourMonday;

    @OneToOne
    private PersonDoctorHourTuesday personDoctorHourTuesday;

    @OneToOne
    private PersonDoctorHourWednesday personDoctorHourWednesday;

    @OneToOne
    private PersonDoctorHourThursday personDoctorHourThursday;

    @OneToOne
    private PersonDoctorHourFriday personDoctorHourFriday;

    @OneToOne
    private PersonDoctorHourSaturday personDoctorHourSaturday;

}
