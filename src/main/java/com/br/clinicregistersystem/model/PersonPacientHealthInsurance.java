package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacientHealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long pacientHealthInsuranceId;

    @Column
    private PersonPacientHealthInsuranceName name;

    @Column
    private Long number;

    @Column
    private PersonPacientHealthInsuranceCoverage coverage;

    @Column
    private OffsetDateTime lastRegister;

}
