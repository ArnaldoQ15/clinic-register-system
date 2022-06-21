package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonAddressInDto;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.service.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/address")
public class PersonAddressController {

    @Autowired
    private PersonAddressService service;


    /**(GET) Find all addresses by person ID.*/
    @GetMapping
    public ResponseEntity<List<PersonAddressOutDto>> findAllById(@Valid @PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findAllById(personId));
    }


    /**(POST) Insert a new address on person.*/
    @Transactional
    @PostMapping("/new")
    public ResponseEntity<PersonAddressOutDto> persist(@Valid @PathVariable Long personId, @RequestBody PersonAddressInDto dto) {
        return service.persist(personId, dto);
    }


    /**(PUT) Update a address on database.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonAddressOutDto> update(@Valid @PathVariable Long personId, @RequestBody PersonAddressInDto dto) {
        return service.update(personId, dto);
    }


    /**(DELETE) Delete a address on database.*/
    @Transactional
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long addressId, @Valid @PathVariable Long personId) {
        return service.delete(addressId, personId);
    }

}
