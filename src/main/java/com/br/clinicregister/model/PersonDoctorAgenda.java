package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@Entity(name = "doctorAgenda")
public class PersonDoctorAgenda {

    @NotNull
    @Id
    @Column(name = "agenda_id")
    private Long agendaId;

    @Column(name = "agenda_scale")
    private PersonDoctorAgendaScales agendaScale;

    @Column(name = "agenda_hours_list")
    private ArrayList<String> hoursList;

    @Column(name = "agenda_appointments")
    private PersonDoctorAgendaAppointments appointments;

    @Column(name = "agenda_hours_available")
    private ArrayList<String> hoursAvailable;


    public PersonDoctorAgenda(PersonDoctorAgendaScales agendaScale) {
        this.agendaScale = agendaScale;
        this.setHoursList();
    }

    public void setAgendaScale(PersonDoctorAgendaScales agendaScale) {
        this.agendaScale = agendaScale;
        this.setHoursList();
    }

    private ArrayList setHoursList() {
        ArrayList<String> listenerHours = new ArrayList<String>();


        if (getAgendaScale().equals(PersonDoctorAgendaScales.MORNING))
            for (PersonDoctorAgendaScalesMorning onlyMorning : PersonDoctorAgendaScalesMorning.values()) {
                listenerHours.add(onlyMorning.getHour());
            }
        else if (getAgendaScale().equals(PersonDoctorAgendaScales.MORNING_AFTERNOON)) {
            for (PersonDoctorAgendaScalesMorningAfternoon morningAfternoon : PersonDoctorAgendaScalesMorningAfternoon.values()) {
                listenerHours.add(morningAfternoon.getHour());
            }
        } else if (getAgendaScale().equals(PersonDoctorAgendaScales.MORNING_AFTERNOON_NIGHT)) {
            for (PersonDoctorAgendaScalesMorningAfternoonNight morningAfternoonNight : PersonDoctorAgendaScalesMorningAfternoonNight.values()) {
                listenerHours.add(morningAfternoonNight.getHour());
            }
        } else if (getAgendaScale().equals(PersonDoctorAgendaScales.MORNING_NIGHT)) {
            for (PersonDoctorAgendaScalesMorningNight morningNight : PersonDoctorAgendaScalesMorningNight.values()) {
                listenerHours.add(morningNight.getHour());
            }
        } else if (getAgendaScale().equals(PersonDoctorAgendaScales.AFTERNOON)) {
            for (PersonDoctorAgendaScalesAfternoon onlyAfternoon : PersonDoctorAgendaScalesAfternoon.values()) {
                listenerHours.add(onlyAfternoon.getHour());
            }
        } else if (getAgendaScale().equals(PersonDoctorAgendaScales.AFTERNOON_NIGHT)) {
            for (PersonDoctorAgendaScalesAfternoonNight afternoonNight : PersonDoctorAgendaScalesAfternoonNight.values()) {
                listenerHours.add(afternoonNight.getHour());
            }
        } else if (getAgendaScale().equals(PersonDoctorAgendaScales.NIGHT)) {
            for (PersonDoctorAgendaScalesNight onlyNight : PersonDoctorAgendaScalesNight.values()) {
                listenerHours.add(onlyNight.getHour());
            }
        } else {
            return null;
        }
        this.hoursList = listenerHours;
        return this.hoursList;
    }

//    public ArrayList setHoursAvailable () {
//        ArrayList<String> listenerAvailableHours = new ArrayList<String>();
//        if (get)
//
//    }

}
