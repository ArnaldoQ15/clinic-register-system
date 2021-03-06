package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPhoneInDto;
import com.br.clinicregistersystem.dto.PersonPhoneOutDto;
import com.br.clinicregistersystem.service.PersonPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/phones")
public class PersonPhoneController {

    @Autowired
    private PersonPhoneService service;


    /**(GET) Find all phones by person ID.*/
    @GetMapping
    public ResponseEntity<List<PersonPhoneOutDto>> findAllById(@Valid @PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findAllById(personId));
    }


    /**(POST) Insert a new phone on person.*/
    @Transactional
    @PostMapping("/new")
    public ResponseEntity<PersonPhoneOutDto> persist(@Valid @PathVariable Long personId, @RequestBody PersonPhoneInDto dto) {
        return service.persist(personId, dto);
    }


    /**(PUT) Update a phone on database.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPhoneOutDto> update(@Valid @PathVariable Long personId, @RequestBody PersonPhoneInDto dto) {
        return service.update(personId, dto);
    }


    /**(DELETE) Delete a phone on database.*/
    @Transactional
    @DeleteMapping("/delete/{phoneId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long phoneId, @Valid @PathVariable Long personId) {
        return service.delete(phoneId, personId);
    }

}
