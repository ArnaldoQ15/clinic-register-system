package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.*;
import com.br.clinicregistersystem.dto.ConsultDto;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DoctorHourService {

    private DoctorHourMondayRepository doctorHourMondayRepository;
    private DoctorHourTuesdayRepository doctorHourTuesdayRepository;
    private DoctorHourWednesdayRepository doctorHourWednesdayRepository;
    private DoctorHourThursdayRepository doctorHourThursdayRepository;
    private DoctorHourFridayRepository doctorHourFridayRepository;
    private DoctorHourSaturdayRepository doctorHourSaturdayRepository;


    List<String> hourDoc = new ArrayList<String>();
    List<String> dayDoc = new ArrayList<String>();
    List<Boolean> dispDoc = new ArrayList<Boolean>();
    List<String> allDocHours = new ArrayList<String>();
    HashMap<String, Boolean> monday = new HashMap<>();


    /**Set doctor agenda list on database.*/
    public void createDoctorAgenda(Doctor doctor) {

        DoctorHourMonday doctorHourMonday = new DoctorHourMonday();
        DoctorHourTuesday doctorHourTuesday = new DoctorHourTuesday();
        DoctorHourWednesday doctorHourWednesday = new DoctorHourWednesday();
        DoctorHourThursday doctorHourThursday = new DoctorHourThursday();
        DoctorHourFriday doctorHourFriday = new DoctorHourFriday();
        DoctorHourSaturday doctorHourSaturday = new DoctorHourSaturday();

        Boolean que = monday.get("08:00 AM");

        switch (doctor.getDoctorEspeciality()) {
            case ANGIOLOGY -> {
                doctorHourMonday.setDoctor(doctor);
                doctorHourMonday.setDoctorName(doctor.getPersonName());
                doctorHourMonday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourMonday.setM0800(true);
                monday.put("08:00 AM", doctorHourMonday.getM0800());
                doctorHourMonday.setM0830(true);
                monday.put("08:30 AM", doctorHourMonday.getM0830());
                doctorHourMonday.setM0900(true);
                monday.put("09:00 AM", doctorHourMonday.getM0900());
                doctorHourMonday.setM0930(true);
                monday.put("09:30 AM", doctorHourMonday.getM0930());
                doctorHourMonday.setM1000(true);
                monday.put("10:00 AM", doctorHourMonday.getM1000());
                doctorHourMonday.setM1030(true);
                monday.put("10:30 AM", doctorHourMonday.getM1030());
                doctorHourMonday.setM1100(true);
                monday.put("11:00 AM", doctorHourMonday.getM1100());
                doctorHourMonday.setM1130(true);
                monday.put("11:30 AM", doctorHourMonday.getM1130());
                doctorHourMonday.setA1400(true);
                monday.put("14:00 PM", doctorHourMonday.getA1400());
                doctorHourMonday.setA1430(true);
                monday.put("14:30 PM", doctorHourMonday.getA1430());
                doctorHourMonday.setA1500(true);
                monday.put("15:00 PM", doctorHourMonday.getA1500());
                doctorHourMonday.setA1530(true);
                monday.put("15:30 PM", doctorHourMonday.getA1530());
                doctorHourMonday.setA1600(true);
                monday.put("16:00 PM", doctorHourMonday.getA1600());
                doctorHourMonday.setA1630(true);
                monday.put("16:30 PM", doctorHourMonday.getA1630());
                doctorHourMonday.setA1700(true);
                monday.put("17:00 PM", doctorHourMonday.getA1700());
                doctorHourMonday.setA1730(true);
                monday.put("17:30 PM", doctorHourMonday.getA1730());
                doctorHourMondayRepository.save(doctorHourMonday);


                doctorHourSaturday.setDoctor(doctor);
                doctorHourSaturday.setDoctorName(doctor.getPersonName());
                doctorHourSaturday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourSaturday.setM0800(true);
                doctorHourSaturday.setM0830(true);
                doctorHourSaturday.setM0900(true);
                doctorHourSaturday.setM0930(true);
                doctorHourSaturday.setM1000(true);
                doctorHourSaturday.setM1030(true);
                doctorHourSaturday.setM1100(true);
                doctorHourSaturday.setM1130(true);
                doctorHourSaturdayRepository.save(doctorHourSaturday);
            }

            case CARDIOLOGY -> {
                doctorHourTuesday.setDoctor(doctor);
                doctorHourTuesday.setDoctorName(doctor.getPersonName());
                doctorHourTuesday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourTuesday.setM0800(true);
                doctorHourTuesday.setM0830(true);
                doctorHourTuesday.setM0900(true);
                doctorHourTuesday.setM0930(true);
                doctorHourTuesday.setM1000(true);
                doctorHourTuesday.setM1030(true);
                doctorHourTuesday.setM1100(true);
                doctorHourTuesday.setM1130(true);
                doctorHourTuesday.setA1400(true);
                doctorHourTuesday.setA1430(true);
                doctorHourTuesday.setA1500(true);
                doctorHourTuesday.setA1530(true);
                doctorHourTuesday.setA1600(true);
                doctorHourTuesday.setA1630(true);
                doctorHourTuesday.setA1700(true);
                doctorHourTuesday.setA1730(true);
                doctorHourTuesday.setN1800(true);
                doctorHourTuesday.setN1830(true);
                doctorHourTuesday.setN1900(true);
                doctorHourTuesday.setN1930(true);
                doctorHourTuesdayRepository.save(doctorHourTuesday);

                doctorHourWednesday.setDoctor(doctor);
                doctorHourWednesday.setDoctorName(doctor.getPersonName());
                doctorHourWednesday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourWednesday.setA1400(true);
                doctorHourWednesday.setA1430(true);
                doctorHourWednesday.setA1500(true);
                doctorHourWednesday.setA1530(true);
                doctorHourWednesday.setA1600(true);
                doctorHourWednesday.setA1630(true);
                doctorHourWednesday.setA1700(true);
                doctorHourWednesday.setA1730(true);
                doctorHourWednesdayRepository.save(doctorHourWednesday);
            }
            case MEDICAL_CLINIC -> {
                doctorHourMonday.setDoctor(doctor);
                doctorHourMonday.setDoctorName(doctor.getPersonName());
                doctorHourMonday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourMonday.setA1400(true);
                doctorHourMonday.setA1430(true);
                doctorHourMonday.setA1500(true);
                doctorHourMonday.setA1530(true);
                doctorHourMonday.setA1600(true);
                doctorHourMonday.setA1630(true);
                doctorHourMonday.setA1700(true);
                doctorHourMonday.setA1730(true);
                doctorHourMonday.setN1800(true);
                doctorHourMonday.setN1830(true);
                doctorHourMonday.setN1900(true);
                doctorHourMonday.setN1930(true);
                doctorHourMondayRepository.save(doctorHourMonday);


                doctorHourThursday.setDoctor(doctor);
                doctorHourThursday.setDoctorName(doctor.getPersonName());
                doctorHourThursday.setDoctorEspeciality(doctor.getDoctorEspeciality());
                doctorHourThursday.setM0800(true);
                doctorHourThursday.setM0830(true);
                doctorHourThursday.setM0900(true);
                doctorHourThursday.setM0930(true);
                doctorHourThursday.setM1000(true);
                doctorHourThursday.setM1030(true);
                doctorHourThursday.setM1100(true);
                doctorHourThursday.setM1130(true);
                doctorHourThursday.setA1400(true);
                doctorHourThursday.setA1430(true);
                doctorHourThursday.setA1500(true);
                doctorHourThursday.setA1530(true);
                doctorHourThursday.setA1600(true);
                doctorHourThursday.setA1630(true);
                doctorHourThursday.setA1700(true);
                doctorHourThursday.setA1730(true);
                doctorHourThursday.setN1800(true);
                doctorHourThursday.setN1830(true);
                doctorHourThursday.setN1900(true);
                doctorHourThursday.setN1930(true);
                doctorHourThursdayRepository.save(doctorHourThursday);
            }
            case DERMATOLOGY -> {
            }
            case SPEECH_THERAPY -> {
            }
            case GASTROENTEROLOGY -> {
            }
            case GYNECOLOGY -> {
            }
            case MASTOLOGY -> {
            }
            case NUTRITION -> {
            }
            case PEDIATRICS -> {
            }
            case PSYCHOLOGY -> {
            }
            case UROLOGY -> {
            }
        }
    }

    /**Check all doctor hours before save the consult.*/
    public void checkDoctorHours(Consult consult) {

        DoctorHourMonday doctorHourMonday = doctorHourMondayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        DoctorHourTuesday doctorHourTuesday = doctorHourTuesdayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        DoctorHourWednesday doctorHourWednesday = doctorHourWednesdayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        DoctorHourThursday doctorHourThursday = doctorHourThursdayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        DoctorHourFriday doctorHourFriday = doctorHourFridayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        DoctorHourSaturday doctorHourSaturday = doctorHourSaturdayRepository.findByDoctorEspeciality(consult.getConsultEspeciality());

        switch (consult.getConsultEspeciality()) {
            case ANGIOLOGY -> {
                if (monday.containsKey("08:00 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM0800(false);
                } else if (monday.containsKey("08:30 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM0830(false);
                } else if (monday.containsKey("09:00 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM0900(false);
                } else if (monday.containsKey("09:30 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM0930(false);
                } else if (monday.containsKey("10:00 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM1000(false);
                } else if (monday.containsKey("10:30 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM1030(false);
                } else if (monday.containsKey("11:00 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM1100(false);
                } else if (monday.containsKey("11:30 AM") && monday.containsValue(true)) {
                    doctorHourMonday.setM1130(false);
                } else if (monday.containsKey("14:00 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1400(false);
                } else if (monday.containsKey("14:30 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1430(false);
                } else if (monday.containsKey("15:00 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1500(false);
                } else if (monday.containsKey("15:30 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1530(false);
                } else if (monday.containsKey("16:00 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1600(false);
                } else if (monday.containsKey("16:30 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1630(false);
                } else if (monday.containsKey("17:00 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1700(false);
                } else if (monday.containsKey("17:30 PM") && monday.containsValue(true)) {
                    doctorHourMonday.setA1730(false);
                }
            }
            case CARDIOLOGY -> {

            }
        }
    }

}

