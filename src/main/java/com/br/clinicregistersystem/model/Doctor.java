package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

@Data
@Entity(name = "personDoctor")
public class Doctor extends Person {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    @JoinColumn
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
    private DoctorEspeciality DoctorEspeciality;

    public Doctor(String personName, Integer personAge, String personEmail, PersonSex personSex, PersonPhone personPhone,
                  Long personDocumentCpf, Long personDocumentRg, PersonAddress personAddress,
                  Integer professionalRegisterNumber, FederativeUnits professionalRegisterState, SimpleDateFormat professionalRegisterValidity, com.br.clinicregistersystem.model.DoctorEspeciality doctorEspeciality) {
        super(personName, personAge, personEmail, personSex, personPhone, personDocumentCpf, personDocumentRg, personAddress);
        this.professionalRegisterNumber = professionalRegisterNumber;
        this.professionalRegisterState = professionalRegisterState;
        this.professionalRegisterValidity = professionalRegisterValidity;
        DoctorEspeciality = doctorEspeciality;
    }

}
