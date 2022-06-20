package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientInDto;
import com.br.clinicregistersystem.dto.PersonPacientOutDto;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.service.PersonPacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacients")
public class PersonPacientController {

    @Autowired
    private PersonPacientService service;


    /**(GET) Find all pacients on database.*/
    @GetMapping
    public ResponseEntity<List<PersonPacientOutDto>> findAllPacients() {
        return ResponseEntity.ok().body(service.findAll());
    }


    /**(GET) Find pacient by Person ID.*/
    @GetMapping("/{personId}")
    public ResponseEntity<PersonPacientOutDto> findId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findId(personId));
    }


    /**(POST) Add new pacient on database.*/
    @PostMapping("/new")
    public ResponseEntity<PersonPacientOutDto> persist(@Valid @RequestBody PersonPacientInDto dto) {
        return service.persist(dto);
    }


    /**(PUT) Update a pacient on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<PersonPacient> updatePacient (@Valid @PathVariable Long personId, @RequestBody PersonPacientInDto dto) {
        return service.update(personId, dto);
    }


    /**(DELETE) Active/inactive a pacient.*/
    @PutMapping("/{personId}/status")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long personId) {
        return service.delete(personId);
    }

}
