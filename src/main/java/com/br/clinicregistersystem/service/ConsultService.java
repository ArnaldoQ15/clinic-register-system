package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.DoctorRepository;
import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.dto.ConsultDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultService {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private DoctorHourService doctorHourService;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private PacientRepository pacientRepository;


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

    /**Save a consult on database.*/
    @Transactional
    public Consult saveConsult (Consult consult, Long personId) {
        consult.setRegisterDate(OffsetDateTime.now());
        consult.setStatus(ConsultStatus.PENDING);
        Optional<Pacient> person = pacientRepository.findById(personId);
        consult.setPacient(person.get());
        Doctor doutor = doctorRepository.findByDoctorEspeciality(consult.getConsultEspeciality());
        consult.setDoctor(doutor);
        Consult map = modelMapper.map(consult, Consult.class);
        doctorHourService.checkDoctorHours(map);
        return consultRepository.save(consult);
    }


}
