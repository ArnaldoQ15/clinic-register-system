package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Calendar;
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
    private Calendar professionalRegisterValidity;

    @NotBlank
    @Column(name = "doctor_especiality")
    private PersonDoctorEspeciality DoctorEspeciality;

    @Column(name = "doctor_agenda")
    private PersonDoctorAgenda personDoctorAgenda;

    public PersonDoctor(String name, Integer age, String email, PersonSex sex, PersonPhone phone,
                        Integer documentCpf, Integer documentRg, PersonAddress address,
                        Integer professionalRegisterNumber, FederativeUnits professionalRegisterState,
                        Calendar professionalRegisterValidity, PersonDoctorEspeciality doctorEspeciality,
                        PersonDoctorAgenda personDoctorAgenda) {

        super(name, age, email, sex, phone, documentCpf, documentRg, address);
        this.professionalRegisterNumber = professionalRegisterNumber;
        this.professionalRegisterState = professionalRegisterState;
        this.professionalRegisterValidity = professionalRegisterValidity;
        DoctorEspeciality = doctorEspeciality;
        this.personDoctorAgenda = personDoctorAgenda;

    }

}
