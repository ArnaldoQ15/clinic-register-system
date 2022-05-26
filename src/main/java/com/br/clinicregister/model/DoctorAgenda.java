package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "doctor_agenda")
public class DoctorAgenda {

    @NotNull
    @Id
    @Column(name = "agenda_id")
    private Long id;

    @Column(name = "agenda_scale")
    private PersonDoctorAgendaScales agendaScale;

}
