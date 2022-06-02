package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity(name = "pacientHealthInsurance")
public class PacientHealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_insurance_id")
    private Long pacientHealthInsuranceId;

    @Column(name = "health_insurance_name")
    private PacientHealthInsuranceName name;

    @Column(name = "health_insurance_number")
    private Long number;

    @Column(name = "health_insurance_coverage")
    private PacientHealthInsuranceCoverage coverage;

    @Column(name = "health_insurance_last_register_date")
    private OffsetDateTime lastRegister;

    public PacientHealthInsurance(PacientHealthInsuranceName name, Long number, PacientHealthInsuranceCoverage coverage) {
        this.name = name;
        this.number = number;
        this.coverage = coverage;
    }

    public PacientHealthInsurance() {

    }

}
