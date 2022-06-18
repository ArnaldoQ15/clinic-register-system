package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.*;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonPacientService {

    @Autowired
    private PersonPacientRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;


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
        Optional<PersonPacient> pacientOptional = repository.findById(personId);
        if (pacientOptional.isEmpty())
            throw new BusinessException("Pacient not found.");

        PersonPacient pacient = pacientOptional.get();
        List<PersonAddressOutDto> addressOutDtos = new ArrayList<>();
        List<PersonPhoneOutDto> phoneOutDtos = new ArrayList<>();

        PersonPacientOutDto pacientOutDto = modelMapper.map(pacient, PersonPacientOutDto.class);
        pacientOutDto.getPersonAddresses().forEach(personAddress -> addressOutDtos.add(modelMapper.map(personAddress,
                PersonAddressOutDto.class)));
        pacientOutDto.getPersonPhones().forEach(personPhone -> phoneOutDtos.add(modelMapper.map(personPhone,
                PersonPhoneOutDto.class)));

        pacientOutDto.setPersonAddresses(addressOutDtos);
        pacientOutDto.setPersonPhones(phoneOutDtos);

        return pacientOutDto;
    }


    /**Save pacient on repository.*/
    @Transactional
    public ResponseEntity<PersonPacientOutDto> persist(PersonPacientInDto dto) {
        personService.validateCpfExists(dto.getPersonDocumentCpf());
        personService.validateEmailExists(dto.getPersonEmail());

        PersonPacient entityNew = new PersonPacient();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        entityNew.setPersonAge(personService.insertAge(entityNew.getPersonBirthday()));

        List<PersonAddress> personAddresses = new ArrayList<>();
        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        personAddresses.forEach(personAddress -> personAddress.setPerson(modelMapper.map(entityNew, Person.class)));
        entityNew.setPersonAddresses(personAddresses);

        List<PersonPhone> personPhones = new ArrayList<>();
        dto.getPersonPhones().forEach(personPhone -> {
            PersonPhone phone = new PersonPhone();
            BeanUtils.copyProperties(personPhone, phone);
            phone.setPerson(modelMapper.map(entityNew, Person.class));
            personPhones.add(phone);
        });
        entityNew.setPersonPhones(personPhones);

        entityNew.getProntuaries().add(modelMapper.map(dto.getProntuary(), PersonPacientProntuary.class));
        entityNew.getProntuaries().forEach(personPacientProntuary -> {
            if (personPacientProntuary.getFirstTime().equals(dto.getProntuary().getFirstTime()) &&
            personPacientProntuary.getSymptoms().equals(dto.getProntuary().getSymptoms())) {
                personPacientProntuary.setRegisterDate(OffsetDateTime.now());
                personPacientProntuary.setPacient(entityNew);
            }
        });

        PersonPacientChild child = new PersonPacientChild();
        BeanUtils.copyProperties(dto.getPacientChildren(), child);
        child.setResponsable(entityNew.getPersonName());
        child.setResponsablePersonId(entityNew.getPersonId());
        child.setPacient(entityNew);
        entityNew.getPacientChildren().add(child);

        entityNew.getPacientChildren().forEach(personPacientChild -> {
            if (personPacientChild.getChildName().equals(dto.getPacientChildren().getChildName()) &&
                    personPacientChild.getChildSex().equals(dto.getPacientChildren().getChildSex())) {
                personPacientChild.setChildAge(personService.insertAge(personPacientChild.getChildBirthday()));
                personPacientChild.setRegisterDate(OffsetDateTime.now());
            }
        });

        PersonPacientHealthInsurance healthInsurance = modelMapper.map(dto.getHealthInsurance(), PersonPacientHealthInsurance.class);
        entityNew.setHealthInsurance(healthInsurance);
        entityNew.getHealthInsurance().setPersonRegisterDate(OffsetDateTime.now());

        entityNew.setPersonStatus(true);
        repository.save(entityNew);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**Update pacient method.*/
    public ResponseEntity<PersonPacient> update(Long personId, PersonPacientInDto dto) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isEmpty())
            throw new BusinessException("Pacient not found.");

        if (Boolean.TRUE.equals(repository.existsByPersonEmail(dto.getPersonEmail())))
            personService.validateEmailExists(dto.getPersonEmail());

        PersonPacient entityNew = new PersonPacient();
        BeanUtils.copyProperties(pacient.get(), entityNew);

        List<PersonAddress> personAddresses = new ArrayList<>();
        List<PersonPhone> personPhones = new ArrayList<>();

        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        dto.getPersonPhones().forEach(personPhone -> personPhones.add(modelMapper.map(personPhone, PersonPhone.class)));

        entityNew.setPersonStatus(dto.getPersonStatus());
        entityNew.setPersonAddresses(personAddresses);
        entityNew.setPersonPhones(personPhones);
        entityNew.setPersonBirthday(dto.getPersonBirthday());
        entityNew.setPersonPhones(personPhones);
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
            repository.save(pacient.get());
            return ResponseEntity.noContent().build();

        } else {
            throw new BusinessException("Pacient not found.");
        }
    }

}
