package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonFuncionaryRole {

    CLINIC_DIRECTOR("Director of clinic"),
    DOCTOR("Doctor"),
    FINANCE_SECRETARY("Secretary of finances"),
    GENERAL_SERVICES("General services"),
    GUARD("Guard"),
    KEEPER("Keeper"),
    MEDICAL_AUXILIARY("Medical auxiliary"),
    RECEPTIONIST("Receptionist");

    private final String description;

    PersonFuncionaryRole(String description) {
        this.description = description;
    }

}
