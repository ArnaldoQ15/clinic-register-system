package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
