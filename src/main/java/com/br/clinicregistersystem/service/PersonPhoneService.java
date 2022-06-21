package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPhoneRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonPhoneInDto;
import com.br.clinicregistersystem.dto.PersonPhoneOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonPhone;
import com.br.clinicregistersystem.util.statics.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonPhoneService {

    @Autowired
    private PersonPhoneRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**Find all phones on database by Person ID.*/
    public List<PersonPhoneOutDto> findAllById(Long personId) {
        List<PersonPhone> phones = repository.findByPersonId(personId);

        List<PersonPhoneOutDto> outDtos = new ArrayList<>();
        phones.forEach(personPhone -> outDtos.add(modelMapper.map(personPhone, PersonPhoneOutDto.class)));

        return outDtos;
    }


    /**Save a phone on database.*/
    public ResponseEntity<PersonPhoneOutDto> persist(Long personId, PersonPhoneInDto dto) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            PersonPhone entityNew = new PersonPhone();
            entityNew.setNumber(dto.getNumber());
            entityNew.setPersonPhoneName(dto.getPersonPhoneName());
            entityNew.setType(dto.getType());
            entityNew.setPerson(person.get());
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setPhoneStatus(true);
            person.get().getPersonPhones().add(entityNew);

            personRepository.saveAndFlush(person.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new NotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }


    /**Update a phone on database.*/
    public ResponseEntity<PersonPhoneOutDto> update(Long personId, PersonPhoneInDto dto) {
        Optional<PersonPhone> phone = repository.findByPersonIdAndPhoneId(personId, dto.getPhoneId());
        if (phone.isPresent()) {
            PersonPhone personPhone = phone.get();
            personPhone.setNumber(dto.getNumber());
            personPhone.setPersonPhoneName(dto.getPersonPhoneName());
            personPhone.setType(dto.getType());
            personPhone.setPhoneStatus(dto.getPhoneStatus());
            personPhone.setLastUpdate(OffsetDateTime.now());

            repository.saveAndFlush(personPhone);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException(ExceptionMessage.PHONE_NOT_FOUND);
    }


    /**Convert a PhoneInDto list to entity.*/
    public List<PersonPhone> convertListToEntity(List<PersonPhoneInDto> dto, Person person) {
        List<PersonPhone> personPhones = new ArrayList<>();
        dto.forEach(personPhone -> {
            PersonPhone phone = new PersonPhone();
            BeanUtils.copyProperties(personPhone, phone);
            phone.setRegisterDate(OffsetDateTime.now());
            phone.setPhoneStatus(true);
            phone.setPerson(person);
            personPhones.add(phone);
        });
        return personPhones;
    }


    /**Delete a phone.*/
    public ResponseEntity<Void> delete(Long phoneId, Long personId) {
        Optional<PersonPhone> phone = repository.findByPersonIdAndPhoneId(personId, phoneId);
        if (phone.isEmpty())
            throw new NotFoundException(ExceptionMessage.PHONE_NOT_FOUND);
        phone.get().setPhoneStatus(false);
        phone.get().setLastUpdate(OffsetDateTime.now());

        repository.save(phone.get());
        return ResponseEntity.noContent().build();
    }

}
