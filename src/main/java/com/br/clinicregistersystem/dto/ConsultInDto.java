package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.DayHour;
import com.br.clinicregistersystem.util.enums.DayWeek;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
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