package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonSex;
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
