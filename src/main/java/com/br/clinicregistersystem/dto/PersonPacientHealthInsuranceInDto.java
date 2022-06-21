package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonPacientHealthInsuranceCoverage;
import com.br.clinicregistersystem.util.enums.PersonPacientHealthInsuranceName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientHealthInsuranceInDto {

    private Long pacientHealthInsuranceId;
    private PersonPacientHealthInsuranceName name;
    private Long number;
    private PersonPacientHealthInsuranceCoverage coverage;

}
