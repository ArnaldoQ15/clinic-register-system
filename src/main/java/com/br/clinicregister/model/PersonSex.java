package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonSex {

    MALE("Male"),
    FEMALE("Female"),
    NOT_ESPECIFIED("Not especified");

    private final String description;

    PersonSex(String description) {
        this.description = description;
    }

}