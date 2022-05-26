package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonDoctorEspeciality {

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

    PersonDoctorEspeciality(String description) {
        this.description = description;
    }

}
