package com.br.clinicregistersystem.model;

import lombok.Getter;

@Getter
public enum PacientHealthInsuranceCoverage {

    BASIC("Basic plan"),
    INTERMEDIARY("Intermediary plan"),
    ADVANCED("Advanced plan"),
    FULL("All-in plan");

    private final String description;

    PacientHealthInsuranceCoverage(String description) {
        this.description = description;
    }

}
