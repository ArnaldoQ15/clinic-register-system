package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConsultDto {

    private Long consultId;
    private String pacientPersonName;
    private String doctorPersonName;
    private ConsultStatus status;
    private DoctorEspeciality consultEspeciality;
    private String consultDateRequest;
    private String consultHourRequest;
    private OffsetDateTime registerDate;


    public ConsultDto(Consult consult) {
        this.consultId = consult.getConsultId();
        this.pacientPersonName = consult.getPacient().getPersonName();
        this.doctorPersonName = consult.getDoctor().getPersonName();
        this.status = consult.getStatus();
        this.consultEspeciality = consult.getConsultEspeciality();
        this.consultDateRequest = consult.getConsultDateRequest();
        this.consultHourRequest = consult.getConsultHourRequest();
        this.registerDate = consult.getRegisterDate();
    }

    public List<ConsultDto> convertToDto(List<Consult> consult) {
        return consult.stream().map(ConsultDto::new).collect(Collectors.toList());
    }

}