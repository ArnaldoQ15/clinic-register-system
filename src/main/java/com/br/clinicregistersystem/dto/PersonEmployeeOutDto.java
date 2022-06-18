package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonEmployeeRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonEmployeeOutDto {

    private String name;
    private PersonEmployeeRole role;
    private LocalDate admissionDate;
    private LocalDate shutdownDate;
    private String institucionalEmail;

}
