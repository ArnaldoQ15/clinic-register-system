package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.dto.PersonPacientInDto;
import com.br.clinicregistersystem.dto.PersonPacientOutDto;
import com.br.clinicregistersystem.dto.PersonPhoneOutDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;


    /**Find all pacients on database.*/
    public List<PersonPacientOutDto> findAll() {
        List<PersonPacient> pacientList = repository.findAll();
        List<PersonPacientOutDto> pacientFinalList = new ArrayList<>();

        pacientList.forEach(personPacient -> {
            List<PersonAddressOutDto> addressOutDtos = new ArrayList<>();
            List<PersonPhoneOutDto> phoneOutDtos = new ArrayList<>();
            PersonPacientOutDto pacientOutDto = modelMapper.map(personPacient, PersonPacientOutDto.class);
            personPacient.getPersonAddresses().forEach(personAddress -> addressOutDtos.add(modelMapper.map(personAddress,
                    PersonAddressOutDto.class)));
            personPacient.getPersonPhones().forEach(personPhone -> phoneOutDtos.add(modelMapper.map(personPhone,
                    PersonPhoneOutDto.class)));

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
        validateCpfExists(dto);
        validateEmailExists(dto);

        PersonPacient entityNew = new PersonPacient();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        insertPacientAge(entityNew);

        List<PersonAddress> personAddresses = new ArrayList<>();
        List<PersonPhone> personPhones = new ArrayList<>();

        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        dto.getPersonPhones().forEach(personPhone -> personPhones.add(modelMapper.map(personPhone, PersonPhone.class)));

        entityNew.setPersonAddresses(personAddresses);
        entityNew.setPersonPhones(personPhones);

        PersonPacientProntuary prontuary = new PersonPacientProntuary();
        prontuary.setFirstTime(dto.getProntuary().getFirstTime());
        prontuary.setRegisterDate(OffsetDateTime.now());
        prontuary.setPersonId(entityNew.getPersonId());
        prontuary.setPacient(entityNew);
        prontuary.setSymptoms(dto.getProntuary().getSymptoms());
        prontuary.setPersonId(entityNew.getPersonId());

        entityNew.setPersonStatus(true);
        entityNew.getProntuaries().add(prontuary);
        entityNew.getHealthInsurance().setPersonId(entityNew.getPersonId());
        entityNew.getPacientChild().setRegisterDate(OffsetDateTime.now());
        insertChildAge(entityNew.getPacientChild());
        entityNew.getPacientChild().setPrintedTerm(false);
        entityNew.getPacientChild().setPersonId(entityNew.getPersonId());

        repository.save(entityNew);
        return ResponseEntity.ok().build();
    }


    /**Insert the pacient age.*/
    public void insertPacientAge(PersonPacient pacient) {
        Period periodAge = Period.between(pacient.getPersonBirthday(), LocalDate.now());
        Integer realPacientAge = Math.abs(periodAge.getYears());
        pacient.setPersonAge(realPacientAge);
    }


    /**Insert the child age.*/
    public void insertChildAge(PersonPacientChild child) {
        Period periodAge = Period.between(child.getChildBirthday(), LocalDate.now());
        Integer realChildAge = Math.abs(periodAge.getYears());
        child.setChildAge(realChildAge);
    }


    /**Validate if a doctor cpf exists on database.*/
    public void validateCpfExists(PersonPacientInDto dto) {
        Optional<PersonPacient> personCpf = repository.findByPersonDocumentCpf(dto.getPersonDocumentCpf());
        if (personCpf.isPresent())
            throw new BusinessException("There is already a pacient registered with this CPF.");
    }


    /**Validate if a doctor e-mail exists on database.*/
    private void validateEmailExists(PersonPacientInDto dto) {
        Optional<PersonPacient> personEmail = repository.findByPersonEmail(dto.getPersonEmail());
        if (personEmail.isPresent() && !personEmail.get().getPersonDocumentCpf().equals(dto.getPersonDocumentCpf()))
            throw new BusinessException("There is already a pacient registered with this e-mail.");
    }


    /**Update pacient's age method.*/
    public void makeBirthday(PersonPacient personPacient) {
        Integer actualAge = personPacient.getPersonAge();
        int month = personPacient.getPersonBirthday().getMonthValue();
        int day = personPacient.getPersonBirthday().getDayOfMonth();
        int thatMonth = LocalDate.now().getMonthValue();
        int thatDay = LocalDate.now().getDayOfMonth();

        if ((day == thatDay) && (month == thatMonth)) {
            actualAge = actualAge + 1;
        }

        personPacient.setPersonAge(actualAge);
    }


    /**Update pacient method.*/
    public ResponseEntity<PersonPacient> update(Long personId, PersonPacientInDto dto) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isEmpty())
            throw new BusinessException("Pacient not found.");

        if (Boolean.TRUE.equals(repository.existsByPersonEmail(dto.getPersonEmail())))
            validateEmailExists(dto);

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
    public void delete(Long personId) {
        Optional<PersonPacient> pacient = repository.findById(personId);
        if (pacient.isPresent()) {
            pacient.get().setPersonStatus(false);
            repository.save(pacient.get());

        } else {
            throw new BusinessException("Pacient not found.");
        }
    }

}
