package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.util.enums.PersonPacientHealthInsuranceCoverage;
import com.br.clinicregistersystem.util.enums.PersonPacientHealthInsuranceName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonPacientHealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long healthInsuranceId;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonPacientHealthInsuranceName name;

    @Column
    private Long number;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonPacientHealthInsuranceCoverage coverage;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime personRegisterDate;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime personLastUpdate;

}
