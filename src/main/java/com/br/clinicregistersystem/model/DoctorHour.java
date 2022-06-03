package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DoctorHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorAgendaId;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private DoctorHourMonday doctorHourMonday;

    @OneToOne
    private DoctorHourTuesday doctorHourTuesday;

    @OneToOne
    private DoctorHourWednesday doctorHourWednesday;

    @OneToOne
    private DoctorHourThursday doctorHourThursday;

    @OneToOne
    private DoctorHourFriday doctorHourFriday;

    @OneToOne
    private DoctorHourSaturday doctorHourSaturday;

}
