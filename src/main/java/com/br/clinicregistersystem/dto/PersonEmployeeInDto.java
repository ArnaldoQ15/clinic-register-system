package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonEmployeeRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonEmployeeInDto {

    private PersonEmployeeRole role;
    private String admissionDate;
    private String institucionalEmail;

}
