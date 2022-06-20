package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonPacientHealthInsuranceCoverage;
import com.br.clinicregistersystem.model.PersonPacientHealthInsuranceName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientHealthInsuranceOutDto {

    private PersonPacientHealthInsuranceName name;
    private Long number;
    private PersonPacientHealthInsuranceCoverage coverage;
    private OffsetDateTime personRegisterDate;
    private OffsetDateTime personLastUpdate;

}
