package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.ConsultOutDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPacient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.br.clinicregistersystem.model.ConsultStatus.*;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository repository;

    @Autowired
    private PersonDoctorRepository doctorRepository;

    @Autowired
    private PersonPacientRepository pacientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonDoctorAgendaService personDoctorAgendaService;


    /**Find all consults on database.*/
    public List<ConsultOutDto> findAll() {
        List<Consult> consults = repository.findAll();
        List<ConsultOutDto> finalConsults = new ArrayList<>();

        consults.forEach(consult -> finalConsults.add(modelMapper.map(consult, ConsultOutDto.class)));
        return finalConsults;
    }


    /**Find a consult by person ID.*/
    public List<ConsultOutDto> findId(Long personId) {
        List<Consult> consults = repository.findByPersonId(personId);
        List<ConsultOutDto> finalConsults = new ArrayList<>();

        consults.forEach(consult -> finalConsults.add(modelMapper.map(consult, ConsultOutDto.class)));
        return finalConsults;
    }


    /**Save a consult on database.*/
    @Transactional
    public ResponseEntity<Consult> persist(ConsultInDto dto, Long personId) {
        Optional<PersonPacient> pacient = pacientRepository.findById(personId);
        if (pacient.isEmpty())
            throw new NotFoundException("Pacient not found.");

        Optional<PersonDoctor> doctor = doctorRepository.findByMedicalEspeciality(dto.getMedicalEspeciality());
        if (doctor.isEmpty())
            throw new NotFoundException("No doctor available.");

        Consult entityNew = new Consult();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonPacient(pacient.get());
        entityNew.setPersonDoctor(doctor.get());

        if (personDoctorAgendaService.validateConsultAvailable(dto, doctor.get().getPersonId()).getStatusCodeValue() == 200) {
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setStatus(PENDING_AUTHORIZATION);
            repository.save(entityNew);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new BadRequestException("Date/hour requested not available.");
    }


    public void deleteByPacient(Long consultId) {
        Optional<Consult> consult = repository.findById(consultId);
        if (consult.isEmpty())
            throw new NotFoundException("Consult not found.");

        if (consult.get().getStatus().equals(PENDING_AUTHORIZATION) || consult.get().getStatus().equals(PENDING_PAYMENT)
                || consult.get().getStatus().equals(APPROVED)) {
            consult.get().setStatus(CANCELED_BY_PACIENT);
            consult.get().setLastStatusUpdate(OffsetDateTime.now());
        }

        else if (consult.get().getStatus().equals(FINISHED) || consult.get().getStatus().equals(CANCELED_BY_PACIENT)
                || consult.get().getStatus().equals(CANCELED_BY_CLINIC)) {
            throw new BadRequestException("Unable to cancel.");
        }
    }


    public void deleteByClinic(Long consultId) {
        Optional<Consult> consult = repository.findById(consultId);
        if (consult.isEmpty())
            throw new NotFoundException("Consult not found.");

        if (consult.get().getStatus().equals(PENDING_AUTHORIZATION) || consult.get().getStatus().equals(PENDING_PAYMENT)
                || consult.get().getStatus().equals(APPROVED)) {
            consult.get().setStatus(CANCELED_BY_CLINIC);
            consult.get().setLastStatusUpdate(OffsetDateTime.now());
        }

        else if (consult.get().getStatus().equals(FINISHED) || consult.get().getStatus().equals(CANCELED_BY_PACIENT)
                || consult.get().getStatus().equals(CANCELED_BY_CLINIC)) {
            throw new BadRequestException("Unable to cancel.");
        }
    }

}