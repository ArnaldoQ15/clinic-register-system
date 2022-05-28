package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import static com.br.clinicregister.model.PersonDoctorAgendaWeekDays.*;

@Getter
@Setter
@Entity(name = "doctorAgenda")
public class PersonDoctorAgenda extends PersonDoctor {

    @NotNull
    @Id
    @Column(name = "agenda_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agendaId;

    @Column(name = "agenda_scale")
    private PersonDoctorAgendaScales agendaScale;

    @Column(name = "agenda_hours_list")
    private ArrayList<String> hoursList;

    @Column(name = "agenda_appointments")
    private PersonDoctorAgendaAppointments appointments;

    @Column(name = "doctor_choosed_days")
    private PersonDoctorAgendaWeekChoosedDays choosedDays;

    @Column(name = "agenda_week_days")
    private ArrayList<String> daysOfDoc;


    public PersonDoctorAgenda(PersonDoctorAgendaScales agendaScale, PersonDoctorAgendaWeekChoosedDays
            choosedDays) {
        this.agendaScale = agendaScale;
        this.setHoursList();
        this.choosedDays = choosedDays;
        this.setDaysOfDoc();
    }

    public void setAgendaScale(PersonDoctorAgendaScales agendaScale) {
        this.agendaScale = agendaScale;
        this.setHoursList();
        this.setDaysOfDoc();
    }

    public ArrayList<String> setDaysOfDoc() {
        ArrayList<String> theseDays = new ArrayList<String>();

        if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_WED)) {
            theseDays.add(MON.getDays());
            theseDays.add(WED.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_WED_FRI)) {
            theseDays.add(MON.getDays());
            theseDays.add(WED.getDays());
            theseDays.add(FRI.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.TUES_THUR)) {
            theseDays.add(TUES.getDays());
            theseDays.add(THUR.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.TUES_THUR_SAT)) {
            theseDays.add(TUES.getDays());
            theseDays.add(THUR.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.WED_FRI)) {
            theseDays.add(WED.getDays());
            theseDays.add(FRI.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.THUR_SAT)) {
            theseDays.add(THUR.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_FRI)) {
            theseDays.add(MON.getDays());
            theseDays.add(FRI.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_SAT)) {
            theseDays.add(MON.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_THUR)) {
            theseDays.add(MON.getDays());
            theseDays.add(THUR.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_THUR_SAT)) {
            theseDays.add(MON.getDays());
            theseDays.add(THUR.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.TUES_FRI)) {
            theseDays.add(TUES.getDays());
            theseDays.add(FRI.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.TUES_SAT)) {
            theseDays.add(TUES.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.WED_SAT)) {
            theseDays.add(WED.getDays());
            theseDays.add(SAT.getDays());
        } else if (this.getChoosedDays().equals(PersonDoctorAgendaWeekChoosedDays.MON_WED_SAT)) {
            theseDays.add(MON.getDays());
            theseDays.add(WED.getDays());
            theseDays.add(SAT.getDays());
        }

            this.daysOfDoc = theseDays;
        return this.daysOfDoc;

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

//    public ArrayList setChoosedWeekDays() {
//        ArrayList<String> listenerChoosed = new ArrayList<String>();
//
//
//    }



//    public ArrayList setHoursAvailable () {
//        ArrayList<String> listenerAvailableHours = new ArrayList<String>();
//        if (get)
//
//    }

}
