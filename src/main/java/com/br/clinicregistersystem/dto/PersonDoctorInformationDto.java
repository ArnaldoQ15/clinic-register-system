package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDoctorInformationDto {

    private String personName;
    private OffsetDateTime personRegisterDate;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;
    private Boolean personStatus;

}
