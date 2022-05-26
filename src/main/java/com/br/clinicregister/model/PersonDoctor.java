package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Table(name = "person_doctor")
public class PersonDoctor extends Person {

    @NotNull
    @Column(name = "professional_register_number")
    private Integer professionalRegisterNumber;

    @NotNull
    @Column(name = "professional_register_state")
    private PersonDoctorProfessionalRegisterState professionalRegisterState;

    @NotNull
    @Column(name = "professional_register_validity")
    private SimpleDateFormat professionalRegisterValidity;

    @NotNull
    @Column(name = "doctor_especiality")
    private PersonDoctorEspeciality DoctorEspeciality;

    @Column(name = "doctor_agenda")
    private DoctorAgenda doctorAgenda;

}
