package com.br.clinicregister.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonDoctorAgendaScalesMorning {

    // Pattern: M (Morning) + Hour + Minutes

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
    M1145("11:45 AM");

    private final String hour;

    PersonDoctorAgendaScalesMorning(String hour) {
        this.hour = hour;
    }

    public static String valueOf() {
        return Arrays.toString(values());
    }
}
