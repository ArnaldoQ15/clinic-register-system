package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPhoneRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonPhoneInDto;
import com.br.clinicregistersystem.dto.PersonPhoneOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonPhone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public List<PersonPhoneOutDto> findAllById(Long personId) {
        List<PersonPhone> phones = repository.findByPersonId(personId);

        List<PersonPhoneOutDto> outDtos = new ArrayList<>();
        phones.forEach(personPhone -> outDtos.add(modelMapper.map(personPhone, PersonPhoneOutDto.class)));

        return outDtos;
    }

    public ResponseEntity<PersonPhoneOutDto> update(Long personId, PersonPhoneInDto dto) {
        Optional<PersonPhone> phone = repository.findByPersonIdAndPhoneId(personId, dto.getPhoneId());
        if (phone.isPresent()) {
            PersonPhone personPhone = phone.get();
            personPhone.setNumber(dto.getNumber());
            personPhone.setPersonPhoneName(dto.getPersonPhoneName());
            personPhone.setType(dto.getType());

            repository.saveAndFlush(personPhone);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Phone not found.");
    }

    public ResponseEntity<PersonPhoneOutDto> persist(Long personId, PersonPhoneInDto dto) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            PersonPhone entityNew = new PersonPhone();
            entityNew.setNumber(dto.getNumber());
            entityNew.setPersonPhoneName(dto.getPersonPhoneName());
            entityNew.setType(dto.getType());
            entityNew.setPerson(person.get());
            person.get().getPersonPhones().add(entityNew);

            personRepository.saveAndFlush(person.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new NotFoundException("User not found.");
    }


    public List<PersonPhone> convertListToEntity(List<PersonPhoneInDto> dto, Person person) {
        List<PersonPhone> personPhones = new ArrayList<>();
        dto.forEach(personPhone -> {
            PersonPhone phone = new PersonPhone();
            BeanUtils.copyProperties(personPhone, phone);
            phone.setPerson(person);
            personPhones.add(phone);
        });
        return personPhones;
    }

}
