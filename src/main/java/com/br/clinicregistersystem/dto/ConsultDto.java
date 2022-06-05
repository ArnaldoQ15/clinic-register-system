package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.ConsultStatus;
import com.br.clinicregistersystem.model.Doctor;
import com.br.clinicregistersystem.model.DoctorEspeciality;
import com.br.clinicregistersystem.model.Pacient;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class ConsultDto {

    private Long consultId;
    private Pacient pacient;
    private Doctor doctor;
    private ConsultStatus status;
    private DoctorEspeciality consultEspeciality;
    private String consultDateRequest;
    private String consultHourRequest;
    private OffsetDateTime registerDate;

}
