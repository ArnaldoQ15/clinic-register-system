package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.FederativeUnits;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDoctorInformationDto {

    private String personName;
    private OffsetDateTime personRegisterDate;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;
    private Boolean personStatus;

}
