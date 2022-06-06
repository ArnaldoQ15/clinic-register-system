package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.Doctor;
import com.br.clinicregistersystem.model.DoctorEspeciality;
import com.br.clinicregistersystem.model.FederativeUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private DoctorEspeciality doctorEspeciality;


    public DoctorInformationDto(Doctor doctor) {
        this.personName = doctor.getPersonName();
        this.personLastRegisterDate = doctor.getPersonLastRegisterDate();
        this.professionalRegisterNumber = doctor.getProfessionalRegisterNumber();
        this.professionalRegisterState = doctor.getProfessionalRegisterState();
        this.professionalRegisterValidity = doctor.getProfessionalRegisterValidity();
        this.doctorEspeciality = doctor.getDoctorEspeciality();
    }

    public List<DoctorInformationDto> convertToDto(List<Doctor> doctor) {
        return doctor.stream().map(DoctorInformationDto::new).collect(Collectors.toList());
    }

}
