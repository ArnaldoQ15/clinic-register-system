package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import com.br.clinicregistersystem.model.PersonDoctor;
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
public class DoctorInformationDto {

    private String personName;
    private OffsetDateTime personLastRegisterDate;
    private Integer professionalRegisterNumber;
    private FederativeUnits professionalRegisterState;
    private LocalDate professionalRegisterValidity;
    private MedicalEspeciality medicalEspeciality;


    public DoctorInformationDto(PersonDoctor personDoctor) {
        this.personName = personDoctor.getPersonName();
        this.personLastRegisterDate = personDoctor.getPersonLastRegisterDate();
        this.professionalRegisterNumber = personDoctor.getProfessionalRegisterNumber();
        this.professionalRegisterState = personDoctor.getProfessionalRegisterState();
        this.professionalRegisterValidity = personDoctor.getProfessionalRegisterValidity();
        this.medicalEspeciality = personDoctor.getMedicalEspeciality();
    }

}
