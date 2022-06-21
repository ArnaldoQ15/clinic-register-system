package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.ConsultStatus;
import com.br.clinicregistersystem.util.enums.DayHour;
import com.br.clinicregistersystem.util.enums.DayWeek;
import com.br.clinicregistersystem.util.enums.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsultOutDto {

    private String pacientPersonName;
    private String doctorPersonName;
    private ConsultStatus status;
    private MedicalEspeciality medicalEspeciality;
    private DayWeek dayRequest;
    private DayHour hourRequest;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastStatusUpdate;

}