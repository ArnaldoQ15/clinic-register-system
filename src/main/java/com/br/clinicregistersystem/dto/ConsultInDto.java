package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.DayHour;
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
public class ConsultInDto {

    private Long consultId;
    private MedicalEspeciality medicalEspeciality;
    private DayWeek dayRequest;
    private DayHour hourRequest;

}