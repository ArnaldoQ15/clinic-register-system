package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonAddressInDto;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.service.PersonAddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/address")
@AllArgsConstructor
public class PersonAddressController {

    @Autowired
    private PersonAddressService service;


    /**(GET) Find all addresses by person ID.*/
    @GetMapping
    public List<PersonAddressOutDto> findAllById(@Valid @PathVariable Long personId) {
        return service.findAllById(personId);
    }


    /**(PUT) Update a address on database.*/
    @PutMapping
    @Transactional
    public ResponseEntity<PersonAddressOutDto> update(@Valid @PathVariable Long personId, @RequestBody PersonAddressInDto dto) {
        return service.update(personId, dto);
    }


    /**(POST) Insert a new address on person.*/
    @PostMapping("/new")
    @Transactional
    public ResponseEntity<PersonAddressOutDto> persist(@Valid @PathVariable Long personId, @RequestBody PersonAddressInDto dto) {
        return service.persist(personId, dto);
    }

}
