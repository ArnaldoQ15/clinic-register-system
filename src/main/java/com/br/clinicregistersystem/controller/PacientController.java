package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.HealthInsuranceRepository;
import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.domain.repository.ProntuaryRepository;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.model.PacientHealthInsurance;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import com.br.clinicregistersystem.service.PacientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pacients")
public class PacientController {

    private PacientRepository pacientRepository;
    private PacientService pacientService;
    private ProntuaryRepository prontuaryRepository;



//    (GET) Find all pacients on database
    @GetMapping
    public List<Pacient> pacientList() {
        return pacientRepository.findAll();
    }



//    (GET) Find pacient by Person ID
    @GetMapping("/{personId}")
    public Pacient searchPersonId(@PathVariable Long personId) {
        return pacientService.searchByPersonId(personId);
    }



//    (POST) Add new pacient on database
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Pacient addPacient (@Valid @RequestBody Pacient pacient) {
        pacient.setPersonLastRegisterDate(OffsetDateTime.now());
        return pacientService.savePacient(pacient);
    }



//    (PUT) Update a pacient on database
    @PutMapping("/{personId}")
    public ResponseEntity<Pacient> updatePacient (@Valid @PathVariable Long personId, @RequestBody Pacient pacient) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacient.setPersonId(personId);
        pacient = pacientService.updatePacients(pacient);
        return ResponseEntity.ok(pacient);
    }



//    (PUT) Active/inactive a pacient
    @PutMapping("/{personId}/status")
    public ResponseEntity<Void> activeStatusPacient (@Valid @PathVariable Long personId) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        pacientService.changeStatusPacient(personId);
        return ResponseEntity.noContent().build();
    }

}
