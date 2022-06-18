package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonAddressRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonAddressInDto;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonAddress;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonAddressService {

    @Autowired
    private PersonAddressRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<PersonAddressOutDto> findAllById(Long personId) {
        List<PersonAddress> addresses = repository.findByPersonId(personId);

        List<PersonAddressOutDto> outDtos = new ArrayList<>();
        addresses.forEach(personAddress -> outDtos.add(modelMapper.map(personAddress, PersonAddressOutDto.class)));

        return outDtos;
    }

    public ResponseEntity<PersonAddressOutDto> update(Long personId, PersonAddressInDto dto) {
        Optional<PersonAddress> address = repository.findByPersonIdAndAddressId(personId, dto.getAddressId());
        if (address.isPresent()) {
            PersonAddress personAddress = address.get();
            personAddress.setPostalCode(dto.getPostalCode());
            personAddress.setCity(dto.getCity());
            personAddress.setComplement(dto.getComplement());
            personAddress.setDistrict(dto.getDistrict());
            personAddress.setNumber(dto.getNumber());
            personAddress.setStreet(dto.getStreet());

            repository.saveAndFlush(personAddress);
            return ResponseEntity.ok().build();
        }
        throw new BusinessException("Address not found.");
    }

    public ResponseEntity<PersonAddressOutDto> persist(Long personId, PersonAddressInDto dto) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            PersonAddress entityNew = modelMapper.map(dto, PersonAddress.class);
            entityNew.setPerson(person.get());
            person.get().getPersonAddresses().add(entityNew);
            personRepository.saveAndFlush(person.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new BusinessException("Person not found.");
    }

}
