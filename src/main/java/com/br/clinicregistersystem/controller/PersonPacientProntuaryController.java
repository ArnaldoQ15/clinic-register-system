package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientProntuaryInDto;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryOutDto;
import com.br.clinicregistersystem.service.PersonProntuaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacients/{personId}/prontuary")
public class PersonPacientProntuaryController {

    @Autowired
    private PersonProntuaryService service;


    /**(GET) Find prontuary by person ID.*/
    @GetMapping
    public ResponseEntity<List<PersonPacientProntuaryOutDto>> findAllById(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findAllById(personId));
    }


    /**(PUT) Update the pacient's prontuary.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientProntuaryOutDto> update(@Valid @PathVariable Long personId,
                                                               @RequestBody PersonPacientProntuaryInDto dto) {
        service.update(dto);
        return ResponseEntity.ok().build();
    }


    /**(POST) Persist a pacient's prontuary.*/
    @Transactional
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonPacientProntuaryOutDto> persist(@Valid @PathVariable Long personId,
                                                                @RequestBody PersonPacientProntuaryInDto dto) {
        service.persist(personId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
