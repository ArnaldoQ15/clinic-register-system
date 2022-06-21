package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonAddressRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonAddressInDto;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonAddress;
import com.br.clinicregistersystem.util.statics.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonAddressService {

    @Autowired
    private PersonAddressRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**Find all addresses on database by Person ID.*/
    public List<PersonAddressOutDto> findAllById(Long personId) {
        List<PersonAddress> addresses = repository.findByPersonId(personId);

        List<PersonAddressOutDto> outDtos = new ArrayList<>();
        addresses.forEach(personAddress -> outDtos.add(modelMapper.map(personAddress, PersonAddressOutDto.class)));

        return outDtos;
    }


    /**Save an address on database.*/
    public ResponseEntity<PersonAddressOutDto> persist(Long personId, PersonAddressInDto dto) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            PersonAddress entityNew = modelMapper.map(dto, PersonAddress.class);
            entityNew.setPerson(person.get());
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setAddressStatus(true);
            person.get().getPersonAddresses().add(entityNew);
            personRepository.saveAndFlush(person.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new NotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }


    /**Update a address on database.*/
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
            personAddress.setAddressStatus(dto.getAddressStatus());
            personAddress.setLastUpdate(OffsetDateTime.now());

            repository.saveAndFlush(personAddress);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND);
    }


    /**Convert a AddressInDto list to entity.*/
    public List<PersonAddress> convertListToEntity(List<PersonAddressInDto> dto, Person person) {
        List<PersonAddress> personAddresses = new ArrayList<>();
        dto.forEach(personAddress -> {
            PersonAddress entityNew = modelMapper.map(personAddress, PersonAddress.class);
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setAddressStatus(true);
            personAddresses.add(entityNew);
        });
        personAddresses.forEach(personAddress -> personAddress.setPerson(person));
        return personAddresses;
    }


    /**Delete an address.*/
    public ResponseEntity<Void> delete(Long addressId, Long personId) {
        Optional<PersonAddress> address = repository.findByPersonIdAndAddressId(personId, addressId);
        if (address.isEmpty())
            throw new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND);
        address.get().setAddressStatus(false);
        address.get().setLastUpdate(OffsetDateTime.now());

        repository.save(address.get());
        return ResponseEntity.noContent().build();
    }

}
