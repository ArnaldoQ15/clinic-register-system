package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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
    private DoctorEspeciality doctorEspeciality;

    public DoctorDto(Doctor doctor) {
        this.personId = doctor.getPersonId();
        this.personName = doctor.getPersonName();
        this.personAge = doctor.getPersonAge();
        this.personEmail = doctor.getPersonEmail();
        this.personSex = doctor.getPersonSex();
        this.personPhones = doctor.getPersonPhones();
        this.personBirthday = doctor.getPersonBirthday();
        this.personDocumentCpf = doctor.getPersonDocumentCpf();
        this.personDocumentRg = doctor.getPersonDocumentRg();
        this.personAddresses = doctor.getPersonAddresses();
        this.personLastRegisterDate = doctor.getPersonLastRegisterDate();
        this.personStatus = doctor.getPersonStatus();
        this.professionalRegisterNumber = doctor.getProfessionalRegisterNumber();
        this.professionalRegisterState = doctor.getProfessionalRegisterState();
        this.professionalRegisterValidity = doctor.getProfessionalRegisterValidity();
        this.doctorEspeciality = doctor.getDoctorEspeciality();
    }

    public List<DoctorDto> convertToDto(List<Doctor> doctor) {
        return doctor.stream().map(DoctorDto::new).collect(Collectors.toList());
    }

}
