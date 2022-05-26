package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonDoctorAgendaScales {

    MORNING("Morning"),
    MORNING_AFTERNOON("Morning and afternoon"),
    MORNING_NIGHT("Morning and night"),
    MORNING_AFTERNOON_NIGHT("Morning, afternoon and night"),
    AFTERNOON("Afternoon"),
    AFTERNOON_NIGHT("Afternoon and night"),
    NIGHT("Night");

    private final String description;

    PersonDoctorAgendaScales(String description) {
        this.description = description;
    }

}
