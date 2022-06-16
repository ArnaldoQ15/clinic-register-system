package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonPacientHealthInsuranceCoverage;
import com.br.clinicregistersystem.model.PersonPacientHealthInsuranceName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientHealthInsuranceInDto {

    private Long pacientHealthInsuranceId;
    private PersonPacientHealthInsuranceName name;
    private Long number;
    private PersonPacientHealthInsuranceCoverage coverage;

}
