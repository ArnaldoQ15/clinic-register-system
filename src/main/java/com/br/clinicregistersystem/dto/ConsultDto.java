package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.ConsultStatus;
import com.br.clinicregistersystem.model.MedicalEspeciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConsultDto {

    private Long consultId;
    private String pacientPersonName;
    private String doctorPersonName;
    private ConsultStatus status;
    private MedicalEspeciality consultEspeciality;
    private String consultDateRequest;
    private String consultHourRequest;
    private OffsetDateTime registerDate;


    public ConsultDto(Consult consult) {
        this.consultId = consult.getConsultId();
        this.pacientPersonName = consult.getPersonPacient().getPersonName();
        this.doctorPersonName = consult.getPersonDoctor().getPersonName();
        this.status = consult.getStatus();
        this.consultEspeciality = consult.getConsultEspeciality();
        this.consultDateRequest = consult.getConsultDateRequest();
        this.consultHourRequest = consult.getConsultHourRequest();
        this.registerDate = consult.getRegisterDate();
    }

}