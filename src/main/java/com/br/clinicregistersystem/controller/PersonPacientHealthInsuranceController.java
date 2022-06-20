package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceInDto;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceOutDto;
import com.br.clinicregistersystem.service.PersonPacientHealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacients/{personId}/health-insurance")
public class PersonPacientHealthInsuranceController {

    @Autowired
    private PersonPacientHealthInsuranceService service;


    /**(GET) Find health insurance by person ID.*/
    @GetMapping
    public ResponseEntity<PersonPacientHealthInsuranceOutDto> findId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findId(personId));
    }


    /**(PUT) Update the pacient's health insurance.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientHealthInsuranceOutDto> update(@Valid @PathVariable Long personId,
                                                                     @RequestBody PersonPacientHealthInsuranceInDto dto) {
        return service.update(personId, dto);
    }

}