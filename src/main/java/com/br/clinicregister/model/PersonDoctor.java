package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Table(name = "person_doctor")
public class PersonDoctor extends Person {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @NotNull
    @Column(name = "professional_register_number")
    private Integer professionalRegisterNumber;

    @NotBlank
    @Column(name = "professional_register_state")
    private FederativeUnits professionalRegisterState;

    @NotNull
    @Column(name = "professional_register_validity")
    private Date professionalRegisterValidity;

    @NotBlank
    @Column(name = "doctor_especiality")
    private PersonDoctorEspeciality DoctorEspeciality;

    @Column(name = "doctor_agenda")
    private PersonDoctorAgenda personDoctorAgenda;

}
