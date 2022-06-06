package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    public Long personId;
    private String personName;
    private Integer personAge;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhone> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private String personDocumentCpf;
    private String personDocumentRg;
    private List<PersonAddress> personAddresses = new ArrayList<>();
    private OffsetDateTime personLastRegisterDate;
    private Boolean personStatus;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;

    public DoctorDto(PersonDoctor personDoctor) {
        this.personId = personDoctor.getPersonId();
        this.personName = personDoctor.getPersonName();
        this.personAge = personDoctor.getPersonAge();
        this.personEmail = personDoctor.getPersonEmail();
        this.personSex = personDoctor.getPersonSex();
        this.personPhones = personDoctor.getPersonPhones();
        this.personBirthday = personDoctor.getPersonBirthday();
        this.personDocumentCpf = personDoctor.getPersonDocumentCpf();
        this.personDocumentRg = personDoctor.getPersonDocumentRg();
        this.personAddresses = personDoctor.getPersonAddresses();
        this.personLastRegisterDate = personDoctor.getPersonLastRegisterDate();
        this.personStatus = personDoctor.getPersonStatus();
        this.professionalRegisterNumber = personDoctor.getProfessionalRegisterNumber();
        this.professionalRegisterState = personDoctor.getProfessionalRegisterState();
        this.professionalRegisterValidity = personDoctor.getProfessionalRegisterValidity();
        this.medicalEspeciality = personDoctor.getMedicalEspeciality();
    }

}
