package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity(name = "pacientHealthInsurance")
public class PersonPacientHealthInsurance {

    @NotBlank
    @Column(name = "health_insurance_name")
    private PersonPacientHealthInsuranceName name;

    @Column(name = "health_insurance_number")
    private Integer number;

    @NotBlank
    @Column(name = "health_insurance_coverage")
    private PersonPacientHealthInsuranceCoverage Coverage;

}
