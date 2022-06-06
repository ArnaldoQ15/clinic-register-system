package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.service.PacientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pacients")
public class PacientController {

    private PersonPacientRepository personPacientRepository;
    private PacientService pacientService;


    /**(GET) Find all pacients on database.*/
    @GetMapping
    public List<PersonPacient> pacientList() {
        return personPacientRepository.findAll();
    }


    /**(GET) Find pacient by Person ID.*/
    @GetMapping("/{personId}")
    public PersonPacient searchPersonId(@PathVariable Long personId) {
        return pacientService.searchByPersonId(personId);
    }


    /**(POST) Add new pacient on database.*/
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonPacient addPacient (@Valid @RequestBody PersonPacient personPacient) {
        personPacient.setPersonLastRegisterDate(OffsetDateTime.now());
        pacientService.validatePersonExists(personPacient);
        return pacientService.savePacient(personPacient);
    }


    /**(PUT) Update a pacient on database.*/
    @PutMapping("/{personId}")
    public ResponseEntity<PersonPacient> updatePacient (@Valid @PathVariable Long personId, @RequestBody PersonPacient personPacient) {
        if (!personPacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        personPacient.setPersonId(personId);
        personPacient = pacientService.updatePacients(personPacient);
        return ResponseEntity.ok(personPacient);
    }


    /**(PUT) Active/inactive a pacient.*/
    @PutMapping("/{personId}/status")
    public ResponseEntity<Void> activeStatusPacient (@Valid @PathVariable Long personId) {
        if (!personPacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        pacientService.changeStatusPacient(personId);
        return ResponseEntity.noContent().build();
    }

}
