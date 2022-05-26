package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "person_pacient_consults")
public class Consults {

    @NotNull
    @Id
    @Column(name = "consults_id")
    private Long id;

    @NotNull
    @Column(name = "consults_especiality")
    private PersonDoctorEspeciality especiality;

    @NotNull
    @Column(name = "consults_doctor")
    private PersonDoctor doctor;

}
