package com.br.clinicregister.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonDoctorAgendaScalesMorningNight {

    // Pattern: M/N (Morning/Night) + Hour + Minutes

    M0800("08:00 AM"),
    M0815("08:15 AM"),
    M0830("08:30 AM"),
    M0845("08:45 AM"),
    M0900("09:00 AM"),
    M0915("09:15 AM"),
    M0930("09:30 AM"),
    M0945("09:45 AM"),
    M1000("10:00 AM"),
    M1015("10:15 AM"),
    M1030("10:30 AM"),
    M1045("10:45 AM"),
    M1100("11:00 AM"),
    M1115("11:15 AM"),
    M1130("11:30 AM"),
    M1145("11:45 AM"),

    N1800("18:00 PM"),
    N1815("18:15 PM"),
    N1830("18:30 PM"),
    N1845("18:45 PM"),
    N1900("19:00 PM"),
    N1915("19:15 PM"),
    N1930("19:30 PM"),
    N1945("19:45 PM");

    private final String hour;

    PersonDoctorAgendaScalesMorningNight(String hour) {
        this.hour = hour;
    }

    public static String valueOf() {
        return Arrays.toString(values());
    }
}
