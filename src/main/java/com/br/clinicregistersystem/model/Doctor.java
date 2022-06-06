package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@Data
@Entity(name = "personDoctor")
public class Doctor extends Person {

    @Column(name = "professional_register_number")
    private Integer professionalRegisterNumber;

    @Column(name = "professional_register_state")
    private FederativeUnits professionalRegisterState;

    @Column(name = "professional_register_validity")
    private LocalDate professionalRegisterValidity;

    @Column(name = "doctor_especiality")
    private DoctorEspeciality doctorEspeciality;


    public Doctor(String personName, String personEmail, PersonSex personSex,
                  List<PersonPhone> personPhone, LocalDate personBirthday, String personDocumentCpf,
                  String personDocumentRg, List<PersonAddress> personAddresses, Integer professionalRegisterNumber,
                  FederativeUnits professionalRegisterState, LocalDate professionalRegisterValidity, DoctorEspeciality doctorEspeciality) {
        super(personName, personEmail, personSex, personPhone, personBirthday, personDocumentCpf, personDocumentRg, personAddresses);
        this.professionalRegisterNumber = professionalRegisterNumber;
        this.professionalRegisterState = professionalRegisterState;
        this.professionalRegisterValidity = professionalRegisterValidity;
        this.doctorEspeciality = doctorEspeciality;
    }

}
