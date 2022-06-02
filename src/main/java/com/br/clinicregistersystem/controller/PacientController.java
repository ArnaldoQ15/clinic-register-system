package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.service.PacientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    private PacientRepository pacientRepository;

    @Autowired
    private PacientService pacientService;

//    Return all pacient list
    @GetMapping
    public List<Pacient> pacientList() {
        return pacientRepository.findAll();
    }


//    Find by Person ID on endpoint
    @GetMapping("/{personId}")
    public ResponseEntity<Pacient> searchPersonId(@PathVariable Long personId) {
        return pacientRepository.findById(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    Add pacient on database
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Pacient addPacient (@Valid @RequestBody Pacient pacient) {
        pacient.setPersonRegisterDate(OffsetDateTime.now());
        return pacientService.savePacient(pacient);
    }


//    Put a pacient on database
    @PutMapping("/{personId}")
    public ResponseEntity<Pacient> updatePacient (@Valid @PathVariable Long personId, @RequestBody Pacient pacient) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacient = pacientService.savePacient(pacient);
        return ResponseEntity.ok(pacient);
    }

//    Delete a pacient on database
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePacient (@PathVariable Long personId) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacientService.inactivePacientById((Pacient) pacientRepository.findByPersonStatus(true));
        return ResponseEntity.noContent().build();
    }

}
