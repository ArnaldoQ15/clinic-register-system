package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.PersonPacientInDto;
import com.br.clinicregistersystem.dto.PersonPacientOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.util.statics.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonPacientService {

    @Autowired
    private PersonPacientRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonAddressService personAddressService;

    @Autowired
    private PersonPhoneService personPhoneService;

    @Autowired
    private PersonProntuaryService personProntuaryService;

    @Autowired
    private PersonPacientChildService personPacientChildService;

    @Autowired
    private PersonPacientHealthInsuranceService personPacientHealthInsuranceService;


    /**Find all pacients on database.*/
    public List<PersonPacientOutDto> findAll() {
        List<PersonPacient> pacientList = repository.findAll();
        List<PersonPacientOutDto> pacientFinalList = new ArrayList<>();

        pacientList.forEach(personPacient -> {
            PersonPacientOutDto pacientOutDto = modelMapper.map(personPacient, PersonPacientOutDto.class);
            pacientFinalList.add(pacientOutDto);
        });

        return pacientFinalList;
    }


    /**Find a pacient by Person ID.*/
    public PersonPacientOutDto findId(Long personId) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isEmpty())
            throw new NotFoundException(ExceptionMessage.PACIENT_NOT_FOUND);

        return modelMapper.map(pacient.get(), PersonPacientOutDto.class);
    }


    /**Save pacient on repository.*/
    @Transactional
    public ResponseEntity<PersonPacientOutDto> persist(PersonPacientInDto dto) {
        personService.validateCpfExists(dto.getPersonDocumentCpf());
        personService.validateEmailExists(dto.getPersonEmail());

        PersonPacient entityNew = new PersonPacient();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonAge(personService.insertAge(entityNew.getPersonBirthday()));
        entityNew.setPersonAddresses(personAddressService.convertListToEntity(dto.getPersonAddresses(),
                modelMapper.map(entityNew, Person.class)));
        entityNew.setPersonPhones(personPhoneService.convertListToEntity(dto.getPersonPhones(),
                modelMapper.map(entityNew, Person.class)));
        entityNew.getProntuaries().add(personProntuaryService.convertToEntity(dto.getProntuary(), entityNew));
        entityNew.getPacientChildren().add(personPacientChildService.convertToEntity(dto.getPacientChildren(), entityNew));
        entityNew.setHealthInsurance(personPacientHealthInsuranceService.convertToEntity(dto.getHealthInsurance()));

        entityNew.setPacientName(dto.getPersonName());
        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        entityNew.setPersonStatus(true);

        repository.saveAndFlush(entityNew);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**Update pacient method.*/
    public ResponseEntity<PersonPacient> update(Long personId, PersonPacientInDto dto) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isEmpty())
            throw new NotFoundException(ExceptionMessage.PACIENT_NOT_FOUND);

        PersonPacient entityNew = new PersonPacient();
        BeanUtils.copyProperties(pacient.get(), entityNew);

        if (!dto.getPersonEmail().equals(entityNew.getPersonEmail())) {
            personService.validateEmailExists(dto.getPersonEmail());
            entityNew.setPersonEmail(dto.getPersonEmail());
        }

        entityNew.setPersonName(dto.getPersonName());
        entityNew.setPacientName(dto.getPersonName());
        entityNew.setPersonStatus(dto.getPersonStatus());
        entityNew.setPersonSex(dto.getPersonSex());
        entityNew.setPersonBirthday(dto.getPersonBirthday());
        entityNew.setPersonLastUpdate(OffsetDateTime.now());

        repository.save(entityNew);
        return ResponseEntity.ok().build();
    }


    /**Inactive/active a pacient by Person ID.*/
    @Transactional
    public ResponseEntity<Void> delete(Long personId) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isPresent()) {
            pacient.get().setPersonStatus(false);
            pacient.get().setPersonLastUpdate(OffsetDateTime.now());
            repository.save(pacient.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException(ExceptionMessage.PACIENT_NOT_FOUND);
        }
    }

}
