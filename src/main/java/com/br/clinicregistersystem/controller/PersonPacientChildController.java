package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonPacientChildInDto;
import com.br.clinicregistersystem.dto.PersonPacientChildOutDto;
import com.br.clinicregistersystem.service.PersonPacientChildService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacients/{personId}/child")
@AllArgsConstructor
public class PersonPacientChildController {

    @Autowired
    private PersonPacientChildService service;

    /**(GET) Find child information by person ID.*/
    @GetMapping
    public List<PersonPacientChildOutDto> findAllById(@PathVariable Long personId) {
        return service.findAllById(personId);
    }


    /**(PUT) Update the pacient's child information.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientChildOutDto> update(@Valid @PathVariable Long personId,
                                                           @RequestBody PersonPacientChildInDto dto) {
        service.update(dto);
        return ResponseEntity.ok().build();
    }

}
