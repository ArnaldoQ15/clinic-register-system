package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientHealthInsuranceRepository;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceInDto;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class PersonPacientHealthInsuranceService {

    @Autowired
    private PersonPacientHealthInsuranceRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    /**Find the pacient's health insurance.*/
    public PersonPacientHealthInsuranceOutDto findId(Long personId) {
        Optional<PersonPacientHealthInsurance> healthInsurance = repository.findByPersonId(personId);
        if (healthInsurance.isEmpty())
            throw new NotFoundException("Pacient's health insurance not found.");

        return modelMapper.map(healthInsurance.get(), PersonPacientHealthInsuranceOutDto.class);
    }


    /**Update the pacient's health insurance.*/
    public ResponseEntity<PersonPacientHealthInsuranceOutDto> update(Long personId, PersonPacientHealthInsuranceInDto dto) {
        Optional<PersonPacientHealthInsurance> healthInsurance = repository.findByPersonId(personId);
        if (healthInsurance.isEmpty())
            throw new NotFoundException("Pacient's health insurance not found.");

        healthInsurance.get().setName(dto.getName());
        healthInsurance.get().setNumber(dto.getNumber());
        healthInsurance.get().setPersonLastUpdate(OffsetDateTime.now());
        healthInsurance.get().setCoverage(dto.getCoverage());
        repository.save(healthInsurance.get());

        return ResponseEntity.ok().build();
    }


    public PersonPacientHealthInsurance convertToEntity(PersonPacientHealthInsuranceInDto dto) {
        PersonPacientHealthInsurance healthInsurance = modelMapper.map(dto, PersonPacientHealthInsurance.class);
        healthInsurance.setPersonRegisterDate(OffsetDateTime.now());
        return healthInsurance;
    }
}