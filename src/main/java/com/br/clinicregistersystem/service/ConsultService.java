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
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultService {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private DoctorHourService doctorHourService;
    private ConsultDto consultDto;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private PacientRepository pacientRepository;


    /**Find a consult by person ID.*/
    public List<ConsultDto> searchByPersonId(Consult consult, Long personId) {
        Optional<Pacient> person = pacientRepository.findById(personId);
        consult.setPacient(person.get());
        consult.setPersonId(person.get().getPersonId());
        List<Consult> consults = consultRepository.findByPersonId(personId);
        return consultDto.convertToDto(consults);
    }


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
