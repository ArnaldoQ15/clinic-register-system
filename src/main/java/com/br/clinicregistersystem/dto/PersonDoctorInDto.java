package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDoctorInDto {

    public Long personId;
    private String personName;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhoneInDto> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private String personDocumentCpf;
    private String personDocumentRg;
    private List<PersonAddressInDto> personAddresses = new ArrayList<>();
    private Boolean personStatus;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;

}
