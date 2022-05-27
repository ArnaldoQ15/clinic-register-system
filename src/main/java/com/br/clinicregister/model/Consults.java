package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "consults")
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
