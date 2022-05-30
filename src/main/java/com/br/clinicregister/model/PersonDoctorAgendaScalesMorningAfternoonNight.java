package com.br.clinicregister.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonDoctorAgendaScalesMorningAfternoonNight {

    // Pattern: M/A/N (Morning/Afternoon/Night) + Hour + Minutes

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

    A1400("14:00 PM"),
    A1415("14:15 PM"),
    A1430("14:30 PM"),
    A1445("14:45 PM"),
    A1500("15:00 PM"),
    A1515("15:15 PM"),
    A1530("15:30 PM"),
    A1545("15:45 PM"),
    A1600("16:00 PM"),
    A1615("16:15 PM"),
    A1630("16:30 PM"),
    A1645("16:45 PM"),
    A1700("17:00 PM"),
    A1715("17:15 PM"),
    A1730("17:30 PM"),
    A1745("17:45 PM"),

    N1800("18:00 PM"),
    N1815("18:15 PM"),
    N1830("18:30 PM"),
    N1845("18:45 PM"),
    N1900("19:00 PM"),
    N1915("19:15 PM"),
    N1930("19:30 PM"),
    N1945("19:45 PM");

    private final String hour;

    PersonDoctorAgendaScalesMorningAfternoonNight(String hour) {
        this.hour = hour;
    }

    public static String valueOf() {
        return Arrays.toString(values());
    }
}
