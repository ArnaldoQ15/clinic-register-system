package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.DayWeek;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDoctorAgendaDto {

    private String doctorName;
    private MedicalEspeciality medicalEspeciality;
    private DayWeek dayWeek;
    private Boolean dataStatus;
    private Boolean morning0800;
    private Boolean morning0830;
    private Boolean morning0900;
    private Boolean morning0930;
    private Boolean morning1000;
    private Boolean morning1030;
    private Boolean morning1100;
    private Boolean morning1130;
    private Boolean afternoon1400;
    private Boolean afternoon1430;
    private Boolean afternoon1500;
    private Boolean afternoon1530;
    private Boolean afternoon1600;
    private Boolean afternoon1630;
    private Boolean afternoon1700;
    private Boolean afternoon1730;
    private Boolean night1800;
    private Boolean night1830;
    private Boolean night1900;
    private Boolean night1930;

}
