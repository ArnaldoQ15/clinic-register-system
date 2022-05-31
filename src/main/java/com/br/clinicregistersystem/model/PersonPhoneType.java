package com.br.clinicregistersystem.model;

import lombok.Getter;

@Getter
public enum PersonPhoneType {

    LANDLINE("Landline phone"),
    CELLPHONE("Cellphone");

    private final String description;

    PersonPhoneType(String description) {
        this.description = description;
    }

}
