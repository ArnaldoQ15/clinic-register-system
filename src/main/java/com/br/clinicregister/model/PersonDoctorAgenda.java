package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@Table(name = "doctor_agenda")
public class PersonDoctorAgenda {

    @NotNull
    @Id
    @Column(name = "agenda_id")
    private Long agendaId;

    @Column(name = "agenda_scale")
    private PersonDoctorAgendaScales agendaScale;

    @Column(name = "agenda_hours_available")
    private ArrayList<String> hoursList;

    public PersonDoctorAgenda(PersonDoctorAgendaScales agendaScale) {
        this.agendaScale = agendaScale;
        this.setHoursList();
    }

//    private void setHoursList() {
//        this.hoursList = hoursList;
//
//        switch (getAgendaScale()) {
//            case MORNING:
//                this.hoursList = String.valueOf((Arrays.toString(PersonDoctorAgendaScalesMorning.values())));
//
//            case MORNING_AFTERNOON:
//                this.hoursList = String.valueOf((Arrays.toString(PersonDoctorAgendaScalesMorningAfternoon.values())));
//
//            case MORNING_AFTERNOON_NIGHT:
//                this.hoursList = String.valueOf((Arrays.toString(PersonDoctorAgendaScalesMorningAfternoonNight.values())));
//
//            case MORNING_NIGHT:
//                this.hoursList = String.valueOf((Arrays.toString(PersonDoctorAgendaScalesMorningNight.values())));
//
//            case AFTERNOON:
//                this.hoursList = Arrays.toString(PersonDoctorAgendaScalesAfternoon.values());
//
//            case AFTERNOON_NIGHT:
//                this.hoursList = Arrays.toString(PersonDoctorAgendaScalesAfternoonNight.values());
//
//            case NIGHT:
//                this.hoursList = String.valueOf((Arrays.toString(PersonDoctorAgendaScalesNight.values())));
//
//            default:
//                this.hoursList = null;
//        }
//
//    }

    private ArrayList setHoursList() {
        ArrayList<String> listenerHours = new ArrayList<String>();


        if (getAgendaScale().equals(PersonDoctorAgendaScales.MORNING))
            for (PersonDoctorAgendaScalesMorning onlyMorning : PersonDoctorAgendaScalesMorning.values()) {
                listenerHours.add(onlyMorning.getHour());
            }

        this.hoursList = listenerHours;

        return this.hoursList;
    }

    public void setAgendaScale(PersonDoctorAgendaScales agendaScale) {
        this.agendaScale = agendaScale;
        this.setHoursList();
    }


}
