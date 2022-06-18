package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.*;
import com.br.clinicregistersystem.service.PersonPhoneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/phones")
@AllArgsConstructor
public class PersonPhoneController {

    @Autowired
    private PersonPhoneService service;


    /**(GET) Find all phones by person ID.*/
    @GetMapping
    public List<PersonPhoneOutDto> findAllById(@Valid @PathVariable Long personId) {
        return service.findAllById(personId);
    }


    /**(PUT) Update a phone on database.*/
    @PutMapping
    @Transactional
    public ResponseEntity<PersonPhoneOutDto> update(@Valid @PathVariable Long personId, @RequestBody PersonPhoneInDto dto) {
        return service.update(personId, dto);
    }


    /**(POST) Insert a new phone on person.*/
    @PostMapping("/new")
    @Transactional
    public ResponseEntity<PersonPhoneOutDto> persist(@Valid @PathVariable Long personId, @RequestBody PersonPhoneInDto dto) {
        return service.persist(personId, dto);
    }

}
