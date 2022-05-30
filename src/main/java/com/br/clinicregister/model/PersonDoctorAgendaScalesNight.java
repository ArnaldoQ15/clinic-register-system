package com.br.clinicregister.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonDoctorAgendaScalesNight {

    // Pattern: N (Night) + Hour + Minutes

    N1800("18:00 PM"),
    N1815("18:15 PM"),
    N1830("18:30 PM"),
    N1845("18:45 PM"),
    N1900("19:00 PM"),
    N1915("19:15 PM"),
    N1930("19:30 PM"),
    N1945("19:45 PM");


    private final String hour;

    PersonDoctorAgendaScalesNight(String hour) {
        this.hour = hour;
    }

    public static String valueOf() {
        return Arrays.toString(values());
    }

}
