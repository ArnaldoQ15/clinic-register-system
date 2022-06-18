package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.*;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDoctorAgendaService {

    @Autowired
    private PersonDoctorHourMondayRepository personDoctorHourMondayRepository;

    @Autowired
    private PersonDoctorHourTuesdayRepository personDoctorHourTuesdayRepository;

    @Autowired
    private PersonDoctorHourWednesdayRepository personDoctorHourWednesdayRepository;

    @Autowired
    private PersonDoctorHourThursdayRepository personDoctorHourThursdayRepository;

    @Autowired
    private PersonDoctorHourFridayRepository personDoctorHourFridayRepository;

    @Autowired
    private PersonDoctorHourSaturdayRepository personDoctorHourSaturdayRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonDoctorRepository doctorRepository;

    HashMap<String, Boolean> mondayHour = new HashMap<>();
    HashMap<String, Boolean> tuesdayHour = new HashMap<>();
    HashMap<String, Boolean> wednesdayHour = new HashMap<>();
    HashMap<String, Boolean> thursdayHour = new HashMap<>();
    HashMap<String, Boolean> fridayHour = new HashMap<>();
    HashMap<String, Boolean> saturdayHour = new HashMap<>();


    /**Set doctor agenda list on database.*/
    public void createDoctorAgenda(PersonDoctorInDto dto) {

        PersonDoctorHourMonday personDoctorHourMonday = new PersonDoctorHourMonday();
        PersonDoctorHourTuesday personDoctorHourTuesday = new PersonDoctorHourTuesday();
        PersonDoctorHourWednesday personDoctorHourWednesday = new PersonDoctorHourWednesday();
        PersonDoctorHourThursday personDoctorHourThursday = new PersonDoctorHourThursday();
        PersonDoctorHourFriday personDoctorHourFriday = new PersonDoctorHourFriday();
        PersonDoctorHourSaturday personDoctorHourSaturday = new PersonDoctorHourSaturday();

        Optional<PersonDoctor> doctorOptional = doctorRepository.findByMedicalEspeciality(dto.getMedicalEspeciality());
        if (doctorOptional.isEmpty())
            throw new BusinessException("Doctor not found.");

        PersonDoctor doctor = doctorOptional.get();

        switch (dto.getMedicalEspeciality()) {
            case ANGIOLOGY -> {
                personDoctorHourMonday.setPersonDoctor(doctor);
                personDoctorHourMonday.setDoctorName(doctor.getPersonName());
                personDoctorHourMonday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourMonday.setM0800(true);
                mondayHour.put("08:00 AM", personDoctorHourMonday.getM0800());
                personDoctorHourMonday.setM0830(true);
                mondayHour.put("08:30 AM", personDoctorHourMonday.getM0830());
                personDoctorHourMonday.setM0900(true);
                mondayHour.put("09:00 AM", personDoctorHourMonday.getM0900());
                personDoctorHourMonday.setM0930(true);
                mondayHour.put("09:30 AM", personDoctorHourMonday.getM0930());
                personDoctorHourMonday.setM1000(true);
                mondayHour.put("10:00 AM", personDoctorHourMonday.getM1000());
                personDoctorHourMonday.setM1030(true);
                mondayHour.put("10:30 AM", personDoctorHourMonday.getM1030());
                personDoctorHourMonday.setM1100(true);
                mondayHour.put("11:00 AM", personDoctorHourMonday.getM1100());
                personDoctorHourMonday.setM1130(true);
                mondayHour.put("11:30 AM", personDoctorHourMonday.getM1130());
                personDoctorHourMonday.setA1400(true);
                mondayHour.put("14:00 PM", personDoctorHourMonday.getA1400());
                personDoctorHourMonday.setA1430(true);
                mondayHour.put("14:30 PM", personDoctorHourMonday.getA1430());
                personDoctorHourMonday.setA1500(true);
                mondayHour.put("15:00 PM", personDoctorHourMonday.getA1500());
                personDoctorHourMonday.setA1530(true);
                mondayHour.put("15:30 PM", personDoctorHourMonday.getA1530());
                personDoctorHourMonday.setA1600(true);
                mondayHour.put("16:00 PM", personDoctorHourMonday.getA1600());
                personDoctorHourMonday.setA1630(true);
                mondayHour.put("16:30 PM", personDoctorHourMonday.getA1630());
                personDoctorHourMonday.setA1700(true);
                mondayHour.put("17:00 PM", personDoctorHourMonday.getA1700());
                personDoctorHourMonday.setA1730(true);
                mondayHour.put("17:30 PM", personDoctorHourMonday.getA1730());
                personDoctorHourMondayRepository.save(personDoctorHourMonday);

                personDoctorHourSaturday.setPersonDoctor(doctor);
                personDoctorHourSaturday.setDoctorName(doctor.getPersonName());
                personDoctorHourSaturday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourSaturday.setM0800(true);
                saturdayHour.put("08:00 AM", personDoctorHourSaturday.getM0800());
                personDoctorHourSaturday.setM0830(true);
                saturdayHour.put("08:30 AM", personDoctorHourSaturday.getM0830());
                personDoctorHourSaturday.setM0900(true);
                saturdayHour.put("09:00 AM", personDoctorHourSaturday.getM0900());
                personDoctorHourSaturday.setM0930(true);
                saturdayHour.put("09:30 AM", personDoctorHourSaturday.getM0930());
                personDoctorHourSaturday.setM1000(true);
                saturdayHour.put("10:00 AM", personDoctorHourSaturday.getM1000());
                personDoctorHourSaturday.setM1030(true);
                saturdayHour.put("10:30 AM", personDoctorHourSaturday.getM1030());
                personDoctorHourSaturday.setM1100(true);
                saturdayHour.put("11:00 AM", personDoctorHourSaturday.getM1100());
                personDoctorHourSaturday.setM1130(true);
                saturdayHour.put("11:30 AM", personDoctorHourSaturday.getM1130());
                personDoctorHourSaturdayRepository.save(personDoctorHourSaturday);
            }

            case CARDIOLOGY -> {
                personDoctorHourTuesday.setPersonDoctor(doctor);
                personDoctorHourTuesday.setDoctorName(doctor.getPersonName());
                personDoctorHourTuesday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourTuesday.setM0800(true);
                tuesdayHour.put("08:00 AM", personDoctorHourTuesday.getM0800());
                personDoctorHourTuesday.setM0830(true);
                tuesdayHour.put("08:30 AM", personDoctorHourTuesday.getM0830());
                personDoctorHourTuesday.setM0900(true);
                tuesdayHour.put("09:00 AM", personDoctorHourTuesday.getM0900());
                personDoctorHourTuesday.setM0930(true);
                tuesdayHour.put("09:30 AM", personDoctorHourTuesday.getM0930());
                personDoctorHourTuesday.setM1000(true);
                tuesdayHour.put("10:00 AM", personDoctorHourTuesday.getM1000());
                personDoctorHourTuesday.setM1030(true);
                tuesdayHour.put("10:30 AM", personDoctorHourTuesday.getM1030());
                personDoctorHourTuesday.setM1100(true);
                tuesdayHour.put("11:00 AM", personDoctorHourTuesday.getM1100());
                personDoctorHourTuesday.setM1130(true);
                tuesdayHour.put("11:30 AM", personDoctorHourTuesday.getM1130());
                personDoctorHourTuesday.setA1400(true);
                tuesdayHour.put("14:00 PM", personDoctorHourTuesday.getA1400());
                personDoctorHourTuesday.setA1430(true);
                tuesdayHour.put("14:30 PM", personDoctorHourTuesday.getA1430());
                personDoctorHourTuesday.setA1500(true);
                tuesdayHour.put("15:00 PM", personDoctorHourTuesday.getA1500());
                personDoctorHourTuesday.setA1530(true);
                tuesdayHour.put("15:30 PM", personDoctorHourTuesday.getA1530());
                personDoctorHourTuesday.setA1600(true);
                tuesdayHour.put("16:00 PM", personDoctorHourTuesday.getA1600());
                personDoctorHourTuesday.setA1630(true);
                tuesdayHour.put("16:30 PM", personDoctorHourTuesday.getA1630());
                personDoctorHourTuesday.setA1700(true);
                tuesdayHour.put("17:00 PM", personDoctorHourTuesday.getA1700());
                personDoctorHourTuesday.setA1730(true);
                tuesdayHour.put("17:30 PM", personDoctorHourTuesday.getA1730());
                personDoctorHourTuesday.setN1800(true);
                tuesdayHour.put("18:00 PM", personDoctorHourTuesday.getN1800());
                personDoctorHourTuesday.setN1830(true);
                tuesdayHour.put("18:30 PM", personDoctorHourTuesday.getN1830());
                personDoctorHourTuesday.setN1900(true);
                tuesdayHour.put("19:00 PM", personDoctorHourTuesday.getN1900());
                personDoctorHourTuesday.setN1930(true);
                tuesdayHour.put("19:30 PM", personDoctorHourTuesday.getN1930());
                personDoctorHourTuesdayRepository.save(personDoctorHourTuesday);

                personDoctorHourWednesday.setPersonDoctor(doctor);
                personDoctorHourWednesday.setDoctorName(doctor.getPersonName());
                personDoctorHourWednesday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourWednesday.setA1400(true);
                wednesdayHour.put("14:00 PM", personDoctorHourWednesday.getA1400());
                personDoctorHourWednesday.setA1430(true);
                wednesdayHour.put("14:30 PM", personDoctorHourWednesday.getA1430());
                personDoctorHourWednesday.setA1500(true);
                wednesdayHour.put("15:00 PM", personDoctorHourWednesday.getA1500());
                personDoctorHourWednesday.setA1530(true);
                wednesdayHour.put("15:30 PM", personDoctorHourWednesday.getA1530());
                personDoctorHourWednesday.setA1600(true);
                wednesdayHour.put("16:00 PM", personDoctorHourWednesday.getA1600());
                personDoctorHourWednesday.setA1630(true);
                wednesdayHour.put("16:30 PM", personDoctorHourWednesday.getA1630());
                personDoctorHourWednesday.setA1700(true);
                wednesdayHour.put("17:00 PM", personDoctorHourWednesday.getA1700());
                personDoctorHourWednesday.setA1730(true);
                wednesdayHour.put("17:00 PM", personDoctorHourWednesday.getA1730());
                personDoctorHourWednesdayRepository.save(personDoctorHourWednesday);
            }
            case MEDICAL_CLINIC -> {
                personDoctorHourMonday.setPersonDoctor(doctor);
                personDoctorHourMonday.setDoctorName(doctor.getPersonName());
                personDoctorHourMonday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourMonday.setA1400(true);
                mondayHour.put("14:00 PM", personDoctorHourMonday.getA1400());
                personDoctorHourMonday.setA1430(true);
                mondayHour.put("14:30 PM", personDoctorHourMonday.getA1430());
                personDoctorHourMonday.setA1500(true);
                mondayHour.put("15:00 PM", personDoctorHourMonday.getA1500());
                personDoctorHourMonday.setA1530(true);
                mondayHour.put("15:30 PM", personDoctorHourMonday.getA1530());
                personDoctorHourMonday.setA1600(true);
                mondayHour.put("16:00 PM", personDoctorHourMonday.getA1600());
                personDoctorHourMonday.setA1630(true);
                mondayHour.put("16:30 PM", personDoctorHourMonday.getA1630());
                personDoctorHourMonday.setA1700(true);
                mondayHour.put("17:00 PM", personDoctorHourMonday.getA1700());
                personDoctorHourMonday.setA1730(true);
                mondayHour.put("17:30 PM", personDoctorHourMonday.getA1730());
                personDoctorHourMonday.setN1800(true);
                mondayHour.put("18:00 PM", personDoctorHourMonday.getN1800());
                personDoctorHourMonday.setN1830(true);
                mondayHour.put("18:30 PM", personDoctorHourMonday.getN1830());
                personDoctorHourMonday.setN1900(true);
                mondayHour.put("19:00 PM", personDoctorHourMonday.getN1900());
                personDoctorHourMonday.setN1930(true);
                mondayHour.put("19:30 PM", personDoctorHourMonday.getN1930());
                personDoctorHourMondayRepository.save(personDoctorHourMonday);

                personDoctorHourThursday.setPersonDoctor(doctor);
                personDoctorHourThursday.setDoctorName(doctor.getPersonName());
                personDoctorHourThursday.setMedicalEspeciality(doctor.getMedicalEspeciality());
                personDoctorHourThursday.setM0800(true);
                thursdayHour.put("08:00 AM", personDoctorHourThursday.getM0800());
                personDoctorHourThursday.setM0830(true);
                thursdayHour.put("08:30 AM", personDoctorHourThursday.getM0830());
                personDoctorHourThursday.setM0900(true);
                thursdayHour.put("09:00 AM", personDoctorHourThursday.getM0900());
                personDoctorHourThursday.setM0930(true);
                thursdayHour.put("09:30 AM", personDoctorHourThursday.getM0930());
                personDoctorHourThursday.setM1000(true);
                thursdayHour.put("10:00 AM", personDoctorHourThursday.getM1000());
                personDoctorHourThursday.setM1030(true);
                thursdayHour.put("10:30 AM", personDoctorHourThursday.getM1030());
                personDoctorHourThursday.setM1100(true);
                thursdayHour.put("11:00 AM", personDoctorHourThursday.getM1100());
                personDoctorHourThursday.setM1130(true);
                thursdayHour.put("11:30 AM", personDoctorHourThursday.getM1130());
                personDoctorHourThursday.setA1400(true);
                thursdayHour.put("14:00 PM", personDoctorHourThursday.getA1400());
                personDoctorHourThursday.setA1430(true);
                thursdayHour.put("14:30 PM", personDoctorHourThursday.getA1430());
                personDoctorHourThursday.setA1500(true);
                thursdayHour.put("15:00 PM", personDoctorHourThursday.getA1500());
                personDoctorHourThursday.setA1530(true);
                thursdayHour.put("15:30 PM", personDoctorHourThursday.getA1530());
                personDoctorHourThursday.setA1600(true);
                thursdayHour.put("16:00 PM", personDoctorHourThursday.getA1600());
                personDoctorHourThursday.setA1630(true);
                thursdayHour.put("16:30 PM", personDoctorHourThursday.getA1630());
                personDoctorHourThursday.setA1700(true);
                thursdayHour.put("17:00 PM", personDoctorHourThursday.getA1700());
                personDoctorHourThursday.setA1730(true);
                thursdayHour.put("17:30 PM", personDoctorHourThursday.getA1730());
                personDoctorHourThursday.setN1800(true);
                thursdayHour.put("18:00 PM", personDoctorHourThursday.getN1800());
                personDoctorHourThursday.setN1830(true);
                thursdayHour.put("18:30 PM", personDoctorHourThursday.getN1830());
                personDoctorHourThursday.setN1900(true);
                thursdayHour.put("19:00 PM", personDoctorHourThursday.getN1900());
                personDoctorHourThursday.setN1930(true);
                thursdayHour.put("19:30 PM", personDoctorHourThursday.getN1930());
                personDoctorHourThursdayRepository.save(personDoctorHourThursday);
            }
//            case DERMATOLOGY -> {
//            }
//            case SPEECH_THERAPY -> {
//            }
//            case GASTROENTEROLOGY -> {
//            }
//            case GYNECOLOGY -> {
//            }
//            case MASTOLOGY -> {
//            }
//            case NUTRITION -> {
//            }
//            case PEDIATRICS -> {
//            }
//            case PSYCHOLOGY -> {
//            }
//            case UROLOGY -> {
//            }
        }
    }


    /**Check all doctor hours before save the consult.*/
    public ResponseEntity<ConsultInDto> checkDoctorHours(ConsultInDto dto) {

        String hourRequested = dto.getConsultHourRequest();

        PersonDoctorHourMonday personDoctorHourMonday = personDoctorHourMondayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        PersonDoctorHourTuesday personDoctorHourTuesday = personDoctorHourTuesdayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        PersonDoctorHourWednesday personDoctorHourWednesday = personDoctorHourWednesdayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        PersonDoctorHourThursday personDoctorHourThursday = personDoctorHourThursdayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        PersonDoctorHourFriday personDoctorHourFriday = personDoctorHourFridayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        PersonDoctorHourSaturday personDoctorHourSaturday = personDoctorHourSaturdayRepository.findByMedicalEspeciality(dto.getConsultEspeciality());

        switch (dto.getConsultEspeciality()) {
            case ANGIOLOGY -> {
                if ((hourRequested.equals("08:00 AM")) && (mondayHour.getOrDefault("08:00 AM", true))) {
                    personDoctorHourMonday.setM0800(false);
                    mondayHour.replace("08:00 AM", personDoctorHourMonday.getM0800());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("08:30 AM")) && (mondayHour.getOrDefault("08:30 AM", true))) {
                    personDoctorHourMonday.setM0830(false);
                    mondayHour.replace("08:30 AM", personDoctorHourMonday.getM0830());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:00 AM")) && (mondayHour.getOrDefault("09:00 AM", true))) {
                    personDoctorHourMonday.setM0900(false);
                    mondayHour.replace("09:00 AM", personDoctorHourMonday.getM0900());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:30 AM")) && (mondayHour.getOrDefault("09:30 AM", true))) {
                    personDoctorHourMonday.setM0930(false);
                    mondayHour.replace("09:30 AM", personDoctorHourMonday.getM0930());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:00 AM")) && (mondayHour.getOrDefault("10:00 AM", true))) {
                    personDoctorHourMonday.setM1000(false);
                    mondayHour.replace("10:00 AM", personDoctorHourMonday.getM1000());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:30 AM")) && (mondayHour.getOrDefault("10:30 AM", true))) {
                    personDoctorHourMonday.setM1030(false);
                    mondayHour.replace("10:30 AM", personDoctorHourMonday.getM1030());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:00 AM")) && (mondayHour.getOrDefault("11:00 AM", true))) {
                    personDoctorHourMonday.setM1100(false);
                    mondayHour.replace("11:00 AM", personDoctorHourMonday.getM1100());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:30 AM")) && (mondayHour.getOrDefault("11:30 AM", true))) {
                    personDoctorHourMonday.setM1130(false);
                    mondayHour.replace("11:30 AM", personDoctorHourMonday.getM1130());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("14:00 PM")) && (mondayHour.getOrDefault("14:00 PM", true))) {
                    personDoctorHourMonday.setA1400(false);
                    mondayHour.replace("14:00 PM", personDoctorHourMonday.getA1400());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("14:30 PM")) && (mondayHour.getOrDefault("14:30 PM", true))) {
                    personDoctorHourMonday.setA1430(false);
                    mondayHour.replace("14:30 PM", personDoctorHourMonday.getA1430());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("15:00 PM")) && (mondayHour.getOrDefault("15:00 PM", true))) {
                    personDoctorHourMonday.setA1500(false);
                    mondayHour.replace("15:00 PM", personDoctorHourMonday.getA1500());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("15:30 PM")) && (mondayHour.getOrDefault("15:30 PM", true))) {
                    personDoctorHourMonday.setA1530(false);
                    mondayHour.replace("15:30 PM", personDoctorHourMonday.getA1530());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("16:00 PM")) && (mondayHour.getOrDefault("16:00 PM", true))) {
                    personDoctorHourMonday.setA1600(false);
                    mondayHour.replace("16:00 PM", personDoctorHourMonday.getA1600());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("16:30 PM")) && (mondayHour.getOrDefault("16:30 PM", true))) {
                    personDoctorHourMonday.setA1630(false);
                    mondayHour.replace("16:30 PM", personDoctorHourMonday.getA1630());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("17:00 PM")) && (mondayHour.getOrDefault("17:00 PM", true))) {
                    personDoctorHourMonday.setA1700(false);
                    mondayHour.replace("17:00 PM", personDoctorHourMonday.getA1700());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("17:30 PM")) && (mondayHour.getOrDefault("17:30 PM", true))) {
                    personDoctorHourMonday.setA1730(false);
                    mondayHour.replace("17:30 PM", personDoctorHourMonday.getA1730());
                    return ResponseEntity.ok().build();
                }

                if ((hourRequested.equals("08:00 AM")) && (saturdayHour.getOrDefault("08:00 AM", true))) {
                    personDoctorHourSaturday.setM0800(false);
                    saturdayHour.replace("08:00 AM", personDoctorHourSaturday.getM0800());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("08:30 AM")) && (saturdayHour.getOrDefault("08:30 AM", true))) {
                    personDoctorHourSaturday.setM0830(false);
                    saturdayHour.replace("08:30 AM", personDoctorHourSaturday.getM0830());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:00 AM")) && (saturdayHour.getOrDefault("09:00 AM", true))) {
                    personDoctorHourSaturday.setM0900(false);
                    saturdayHour.replace("09:00 AM", personDoctorHourSaturday.getM0900());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:30 AM")) && (saturdayHour.getOrDefault("09:30 AM", true))) {
                    personDoctorHourSaturday.setM0930(false);
                    saturdayHour.replace("09:30 AM", personDoctorHourSaturday.getM0930());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:00 AM")) && (saturdayHour.getOrDefault("10:00 AM", true))) {
                    personDoctorHourSaturday.setM1000(false);
                    saturdayHour.replace("10:00 AM", personDoctorHourSaturday.getM1000());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:30 AM")) && (saturdayHour.getOrDefault("10:30 AM", true))) {
                    personDoctorHourSaturday.setM1030(false);
                    saturdayHour.replace("10:30 AM", personDoctorHourSaturday.getM1030());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:00 AM")) && (saturdayHour.getOrDefault("11:00 AM", true))) {
                    personDoctorHourSaturday.setM1100(false);
                    saturdayHour.replace("11:00 AM", personDoctorHourSaturday.getM1100());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:30 AM")) && (saturdayHour.getOrDefault("11:30 AM", true))) {
                    personDoctorHourSaturday.setM1130(false);
                    saturdayHour.replace("11:30 AM", personDoctorHourSaturday.getM1130());
                    return ResponseEntity.ok().build();
                }
                return ResponseEntity.badRequest().build();
            }

            case CARDIOLOGY -> {
                if ((hourRequested.equals("08:00 AM")) && (tuesdayHour.getOrDefault("08:00 AM", true))) {
                    personDoctorHourTuesday.setM0800(false);
                    tuesdayHour.replace("08:00 AM", personDoctorHourTuesday.getM0800());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("08:30 AM")) && (tuesdayHour.getOrDefault("08:30 AM", true))) {
                    personDoctorHourTuesday.setM0830(false);
                    tuesdayHour.replace("08:30 AM", personDoctorHourTuesday.getM0830());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:00 AM")) && (tuesdayHour.getOrDefault("09:00 AM", true))) {
                    personDoctorHourTuesday.setM0900(false);
                    tuesdayHour.replace("09:00 AM", personDoctorHourTuesday.getM0900());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("09:30 AM")) && (tuesdayHour.getOrDefault("09:30 AM", true))) {
                    personDoctorHourTuesday.setM0930(false);
                    tuesdayHour.replace("09:30 AM", personDoctorHourTuesday.getM0930());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:00 AM")) && (tuesdayHour.getOrDefault("10:00 AM", true))) {
                    personDoctorHourTuesday.setM1000(false);
                    tuesdayHour.replace("10:00 AM", personDoctorHourTuesday.getM1000());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("10:30 AM")) && (tuesdayHour.getOrDefault("10:30 AM", true))) {
                    personDoctorHourTuesday.setM1030(false);
                    tuesdayHour.replace("10:30 AM", personDoctorHourTuesday.getM1030());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:00 AM")) && (tuesdayHour.getOrDefault("11:00 AM", true))) {
                    personDoctorHourTuesday.setM1100(false);
                    tuesdayHour.replace("11:00 AM", personDoctorHourTuesday.getM1100());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("11:30 AM")) && (tuesdayHour.getOrDefault("11:30 AM", true))) {
                    personDoctorHourTuesday.setM1130(false);
                    tuesdayHour.replace("11:30 AM", personDoctorHourTuesday.getM1130());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("14:00 PM")) && (tuesdayHour.getOrDefault("14:00 PM", true))) {
                    personDoctorHourTuesday.setA1400(false);
                    tuesdayHour.replace("14:00 PM", personDoctorHourTuesday.getA1400());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("14:30 PM")) && (tuesdayHour.getOrDefault("14:30 PM", true))) {
                    personDoctorHourTuesday.setA1430(false);
                    tuesdayHour.replace("14:30 PM", personDoctorHourTuesday.getA1430());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("15:00 PM")) && (tuesdayHour.getOrDefault("15:00 PM", true))) {
                    personDoctorHourTuesday.setA1500(false);
                    tuesdayHour.replace("15:00 PM", personDoctorHourTuesday.getA1500());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("15:30 PM")) && (tuesdayHour.getOrDefault("15:30 PM", true))) {
                    personDoctorHourTuesday.setA1530(false);
                    tuesdayHour.replace("15:30 PM", personDoctorHourTuesday.getA1530());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("16:00 PM")) && (tuesdayHour.getOrDefault("16:00 PM", true))) {
                    personDoctorHourTuesday.setA1600(false);
                    tuesdayHour.replace("16:00 PM", personDoctorHourTuesday.getA1600());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("16:30 PM")) && (tuesdayHour.getOrDefault("16:30 PM", true))) {
                    personDoctorHourTuesday.setA1630(false);
                    tuesdayHour.replace("16:30 PM", personDoctorHourTuesday.getA1630());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("17:00 PM")) && (tuesdayHour.getOrDefault("17:00 PM", true))) {
                    personDoctorHourTuesday.setA1700(false);
                    tuesdayHour.replace("17:00 PM", personDoctorHourTuesday.getA1700());
                    return ResponseEntity.ok().build();
                } else if ((hourRequested.equals("17:30 PM")) && (tuesdayHour.getOrDefault("17:30 PM", true))) {
                    personDoctorHourTuesday.setA1730(false);
                    tuesdayHour.replace("17:30 PM", personDoctorHourTuesday.getA1730());
                    return ResponseEntity.ok().build();
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
}