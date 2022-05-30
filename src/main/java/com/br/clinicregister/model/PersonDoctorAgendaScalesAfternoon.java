package com.br.clinicregister.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonDoctorAgendaScalesAfternoon {

    // Pattern: A (Afternoon) + Hour + Minutes

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
    A1745("17:45 PM");


    private final String hour;

    PersonDoctorAgendaScalesAfternoon(String hour) {
        this.hour = hour;
    }

    public static String valueOf() {
        return Arrays.toString(values());
    }

}
