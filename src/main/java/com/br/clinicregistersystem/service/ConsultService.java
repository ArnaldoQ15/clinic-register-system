package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.DoctorRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ConsultService {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private DoctorHourService doctorHourService;

//    Find a consult by person ID
    public Consult searchByPersonId(Long personId) {
        return consultRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("No consult found."));
    }

//    public Doctor returnADoc(Doctor doctor, Consult consult) {
//        if (doctorRepository.findByDoctorEspeciality(doctor.getDoctorEspeciality().getDescription()).contains(consult.getConsultEspeciality())) {
//            consult.setDoctor(doctor);
//        }
//        return doctor;
//    }

//    Save a consult on database
    @Transactional
    public Consult saveConsult (Consult consult, Doctor doctor, DoctorHourMonday doctorHourMonday, DoctorHourTuesday doctorHourTuesday,
                                DoctorHourWednesday doctorHourWednesday, DoctorHourThursday doctorHourThursday,
                                DoctorHourFriday doctorHourFriday, DoctorHourSaturday doctorHourSaturday) {
        consult.setRegisterDate(OffsetDateTime.now());
        consult.setStatus(ConsultStatus.PENDING);

//        doctorRepository.findByDoctorEspeciality(doctor.getDoctorEspeciality().getDescription());

//        consult.setDoctor(returnADoc(doctor, consult));

        List<String> receiver =  new ArrayList<>();

//        List<Doctor> guarda = doctorRepository.findByDoctorEspeciality(consult.getConsultEspeciality());

//        String[] testando = guarda.toArray(new String[99]);


        doctorHourService.checkDocHours(consult, doctor, doctorHourMonday, doctorHourTuesday, doctorHourWednesday, doctorHourThursday,
                doctorHourFriday, doctorHourSaturday);

        return consultRepository.save(consult);
    }


}
