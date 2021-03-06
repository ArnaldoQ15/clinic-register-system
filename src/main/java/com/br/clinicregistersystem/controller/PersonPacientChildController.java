package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientChildInDto;
import com.br.clinicregistersystem.dto.PersonPacientChildOutDto;
import com.br.clinicregistersystem.service.PersonPacientChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacients/{personId}/child")
public class PersonPacientChildController {

    @Autowired
    private PersonPacientChildService service;

    /**(GET) Find child information by person ID.*/
    @GetMapping
    public ResponseEntity<List<PersonPacientChildOutDto>> findAllById(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findAllById(personId));
    }


    /**(PUT) Update the pacient's child information.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientChildOutDto> update(@Valid @PathVariable Long personId,
                                                           @RequestBody PersonPacientChildInDto dto) {
        return service.update(dto);
    }

}
