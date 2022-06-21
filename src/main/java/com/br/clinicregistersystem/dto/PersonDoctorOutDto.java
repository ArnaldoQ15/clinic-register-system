package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.FederativeUnits;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
import com.br.clinicregistersystem.util.enums.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDoctorOutDto {

    private String personName;
    private Integer personAge;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhoneOutDto> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private List<PersonAddressOutDto> personAddresses = new ArrayList<>();
    private OffsetDateTime personRegisterDate;
    private OffsetDateTime personLastUpdate;
    private Boolean personStatus;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;

}
