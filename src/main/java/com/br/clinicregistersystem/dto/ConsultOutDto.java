package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.ConsultStatus;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultOutDto {

    private String pacientPersonName;
    private String doctorPersonName;
    private ConsultStatus status;
    private MedicalEspeciality consultEspeciality;
    private String consultDateRequest;
    private String consultHourRequest;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastStatusUpdate;

}