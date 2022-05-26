package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "doctor_agenda")
public class PersonDoctorAgenda extends PersonDoctor {

    @NotNull
    @Id
    @Column(name = "agenda_id")
    private Long id;

    @Column(name = "agenda_scale")
    private PersonDoctorAgendaScales agendaScale;

    @Column(name = "agenda_hours_available")
    private String hoursList;

    private String hourBusy;

    private void setHoursList() {
        this.hoursList = hoursList;

        switch (getAgendaScale()) {
            case MORNING:
                this.setHoursList(PersonDoctorAgendaScalesMorning.valueOf());

            case MORNING_AFTERNOON:
                this.setHoursList(PersonDoctorAgendaScalesMorningAfternoon.valueOf());

            case MORNING_AFTERNOON_NIGHT:
                this.setHoursList(PersonDoctorAgendaScalesMorningAfternoonNight.valueOf());

            case MORNING_NIGHT:
                this.setHoursList(PersonDoctorAgendaScalesMorningNight.valueOf());

            case AFTERNOON:
                this.setHoursList(PersonDoctorAgendaScalesAfternoon.valueOf());

            case AFTERNOON_NIGHT:
                this.setHoursList(PersonDoctorAgendaScalesAfternoonNight.valueOf());

            case NIGHT:
                this.setHoursList(PersonDoctorAgendaScalesNight.valueOf());

            default:
                this.setHoursList(null);
        }

    }

}
