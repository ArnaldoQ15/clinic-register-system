package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.service.PacientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pacients")
public class PacientController {

    private PacientRepository pacientRepository;
    private PacientService pacientService;

//    Return all pacient list
    @GetMapping
    public List<Pacient> pacientList() {
        return pacientRepository.findAll();
    }

//    Find by Pacient ID on endpoint
    @GetMapping("/{pacientId}")
    public ResponseEntity<Pacient> searchAllPacientId(@PathVariable Long pacientId) {
        return pacientRepository.findById(pacientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    Add pacient on database
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Pacient addPacient (@Valid @RequestBody Pacient pacient) {
        return pacientService.savePacient(pacient);
    }

//    Put a pacient on database
    @PutMapping("/{pacientId}")
    public ResponseEntity<Pacient> updatePacient (@Valid @PathVariable Long pacientId, @RequestBody Pacient pacient) {
        if (!pacientRepository.existsById(pacientId)) {
            return ResponseEntity.notFound().build();
        }

        pacient.setPacientId(pacientId);
        pacient = pacientService.savePacient(pacient);
        return ResponseEntity.ok(pacient);
    }

//    Delete a pacient on database
    @DeleteMapping("/{pacientId}")
    public ResponseEntity<Void> deletePacient (@PathVariable Long pacientId) {
        if (!pacientRepository.existsById(pacientId)) {
            return ResponseEntity.notFound().build();
        }

        pacientRepository.deleteById(pacientId);
        return ResponseEntity.noContent().build();
    }

}
