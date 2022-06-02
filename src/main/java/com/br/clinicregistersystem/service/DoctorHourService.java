package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.DoctorHourRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.Doctor;
import com.br.clinicregistersystem.model.DoctorHour;
import com.br.clinicregistersystem.model.DoctorScalesEspeciality;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DoctorHourService {

    private DoctorHourRepository doctorHourRepository;

    List<String> hourDoc = new ArrayList<String>();
    List<String> dayDoc = new ArrayList<String>();
    List<Boolean> dispDoc = new ArrayList<Boolean>();



//    Find by Person ID
    public DoctorHour searchByPersonId(Long personId) {
        return doctorHourRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Doctor's agenda not found."));
    }


////    Find by currently doctor's appointments
//    public Boolean haveSpace(Consult consult, DoctorHour doctorHour) {
//
//        if ((dayDoc.contains(consult.getConsultDateRequest())) && (hourDoc.contains(consult.getConsultHourRequest()))) {
//            Integer positionOnIndex = hourDoc.indexOf(consult.getConsultHourRequest());
//
//            if (dispDoc.get(positionOnIndex).equals(true)) {
//
//                if (consult.getConsultDateRequest() == "monday") {
//                    doctorHour.setMonday();
//                }
//            }
//        }
//    }


//    Set boolean list on database
    public void createDoctorAgenda(Doctor doctor, DoctorHour doctorHour) {
        switch (doctor.getDoctorEspeciality()) {
            case ANGIOLOGY -> {
                doctorHour.setMonday(true);
                doctorHour.setM0800(true);
                doctorHour.setM0830(true);
                doctorHour.setM0900(true);
                doctorHourRepository.save(doctorHour);
                doctorHour.setSaturday(true);
                doctorHour.setM0800(true);
                doctorHour.setM0830(true);
                doctorHour.setM0900(true);
                doctorHour.setM1000(true);
                doctorHour.setM1030(true);
                doctorHourRepository.save(doctorHour);
            }
        }
    }



//    Set list on database
    public void createDoctorAgenda(Consult consult) {

        for (DoctorScalesEspeciality runnerForVar : DoctorScalesEspeciality.values()) {
            if ((Objects.equals(runnerForVar.getEspeciality(), consult.getConsultEspeciality())) &&
                    (Objects.equals(runnerForVar.getDay(), consult.getConsultDateRequest()))) {
                hourDoc.add(runnerForVar.getHour());
                dayDoc.add(runnerForVar.getDay());
            }
        }


        for (DoctorScalesEspeciality nomes : DoctorScalesEspeciality.values()) {
            if ((nomes.getEspeciality() == consult.getConsultEspeciality()) &&
                    (nomes.getDay() == consult.getConsultDateRequest())) {
                if (dispDoc.equals(false)) {
                } else {
                    dispDoc.add(true);
                }
            }
        }
    }

}
