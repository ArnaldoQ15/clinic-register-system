package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "pacientHealthInsurance")
public class PersonPacientHealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_insurance_id")
    private Long pacientHealthInsuranceId;

    @Column(name = "health_insurance_name")
    private PersonPacientHealthInsuranceName name;

    @Column(name = "health_insurance_number")
    private Integer number;

    @Column(name = "health_insurance_coverage")
    private PersonPacientHealthInsuranceCoverage Coverage;

}
