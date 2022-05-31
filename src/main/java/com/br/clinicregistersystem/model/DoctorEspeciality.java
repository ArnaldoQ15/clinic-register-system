package com.br.clinicregistersystem.model;

import lombok.Getter;

@Getter
public enum DoctorEspeciality {

    ANGIOLOGY("Angiology"),
    CARDIOLOGY("Cardiology"),
    MEDICAL_CLINIC("Medical clinic"),
    DERMATOLOGY("Dermatology"),
    SPEECH_THERAPY("Speech therapy"),
    GASTROENTEROLOGY("Gastroenterology"),
    GYNECOLOGY("Gynecology"),
    MASTOLOGY("Mastology"),
    NUTRITION("Nutrition"),
    PEDIATRICS("Pediatrics"),
    PSYCHOLOGY("Psychology"),
    UROLOGY("Urology");

    private final String description;

    DoctorEspeciality(String description) {
        this.description = description;
    }

}
