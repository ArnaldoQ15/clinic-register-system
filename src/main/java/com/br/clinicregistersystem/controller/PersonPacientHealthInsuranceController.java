package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceInDto;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceOutDto;
import com.br.clinicregistersystem.service.PersonPacientHealthInsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/pacients/{personId}/health-insurance")
public class PersonPacientHealthInsuranceController {

    @Autowired
    private PersonPacientHealthInsuranceService service;


    /**(PUT) Find health insurance by person ID.*/
    @GetMapping
    public PersonPacientHealthInsuranceOutDto findId(@PathVariable Long personId) {
        return service.findId(personId);
    }


    /**(PUT) Update the pacient's health insurance.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientHealthInsuranceOutDto> update(@Valid @PathVariable Long personId,
                                                                     @RequestBody PersonPacientHealthInsuranceInDto dto) {
        service.update(personId, dto);
        return ResponseEntity.ok().build();
    }

}
