package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "personDoctor")
public class Doctor extends Person {

    @NotNull
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
    private LocalDate professionalRegisterValidity;

    @NotBlank
    @Column(name = "doctor_especiality")
    private DoctorEspeciality DoctorEspeciality;

    public Doctor(String personName, Integer personAge, String personEmail, PersonSex personSex,
                  List<PersonPhone> personPhone, LocalDate personBirthday, Long personDocumentCpf,
                  Long personDocumentRg, List<PersonAddress> personAddresses, Integer professionalRegisterNumber,
                  FederativeUnits professionalRegisterState, LocalDate professionalRegisterValidity, DoctorEspeciality doctorEspeciality) {
        super(personName, personAge, personEmail, personSex, personPhone, personBirthday, personDocumentCpf, personDocumentRg, personAddresses);
        this.professionalRegisterNumber = professionalRegisterNumber;
        this.professionalRegisterState = professionalRegisterState;
        this.professionalRegisterValidity = professionalRegisterValidity;
        DoctorEspeciality = doctorEspeciality;
    }

}
