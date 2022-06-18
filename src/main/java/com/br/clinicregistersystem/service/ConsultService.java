package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.ConsultOutDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.ConsultStatus;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPacient;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.br.clinicregistersystem.model.ConsultStatus.*;

@Service
@AllArgsConstructor
public class ConsultService {

    @Autowired
    private ConsultRepository repository;

    @Autowired
    private PersonDoctorRepository doctorRepository;

    @Autowired
    private PersonDoctorAgendaService personDoctorAgendaService;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private PersonPacientRepository pacientRepository;


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
            throw new BusinessException("Pacient not found.");

        Optional<PersonDoctor> doctor = doctorRepository.findByMedicalEspeciality(dto.getConsultEspeciality());
        if (doctor.isEmpty())
            throw new BusinessException("No doctor available.");

        Consult entityNew = new Consult();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonPacient(pacient.get());
        entityNew.setPersonDoctor(doctor.get());

        if (personDoctorAgendaService.checkDoctorHours(dto).getStatusCodeValue() == 200) {
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setStatus(PENDING);
            repository.save(entityNew);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new BusinessException("Date/hour requested not available.");
    }

}