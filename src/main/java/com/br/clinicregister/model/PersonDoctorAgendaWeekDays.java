package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonDoctorAgendaWeekDays {

    MON("Monday"),
    TUES("Tuesday"),
    WED("Wednesday"),
    THUR("Thursday"),
    FRI("Friday"),
    SAT("Saturday");

    private final String days;

    PersonDoctorAgendaWeekDays(String days) {
        this.days = days;
    }

}
