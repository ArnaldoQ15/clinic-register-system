package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

import static com.br.clinicregister.model.PersonDoctorAgendaWeekDays.*;
import static com.br.clinicregister.model.PersonDoctorAgendaWeekDays.valueOf;

@Getter
@Setter
@Entity(name = "personDoctor")
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
    private SimpleDateFormat professionalRegisterValidity;

    @NotBlank
    @Column(name = "doctor_especiality")
    private PersonDoctorEspeciality DoctorEspeciality;

    @Column(name = "doctor_agenda")
    private PersonDoctorAgenda personDoctorAgenda;

    public PersonDoctor() {
    }

    public PersonDoctor(String name, Integer age, String email, PersonSex sex, PersonPhone phone,
                        Integer documentCpf, Integer documentRg, PersonAddress address,
                        Integer professionalRegisterNumber, FederativeUnits professionalRegisterState,
                        SimpleDateFormat professionalRegisterValidity, PersonDoctorEspeciality doctorEspeciality,
                        PersonDoctorAgenda personDoctorAgenda) {

        super(name, age, email, sex, phone, documentCpf, documentRg, address);
        this.professionalRegisterNumber = professionalRegisterNumber;
        this.professionalRegisterState = professionalRegisterState;
        this.professionalRegisterValidity = professionalRegisterValidity;
        DoctorEspeciality = doctorEspeciality;
        this.personDoctorAgenda = personDoctorAgenda;

    }

}
