package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonPacientHealthInsuranceCoverage {

    BASIC("Basic plan"),
    INTERMEDIARY("Intermediary plan"),
    ADVANCED("Advanced plan"),
    FULL("All-in plan");

    private final String description;

    PersonPacientHealthInsuranceCoverage(String description) {
        this.description = description;
    }
}
