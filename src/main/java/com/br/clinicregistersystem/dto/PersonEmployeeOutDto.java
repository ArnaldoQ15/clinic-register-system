package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonEmployeeRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonEmployeeOutDto {

    private String name;
    private PersonEmployeeRole role;
    private String institucionalEmail;

}
