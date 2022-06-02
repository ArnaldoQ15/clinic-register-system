package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.HealthInsuranceRepository;
import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.domain.repository.ProntuaryRepository;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.model.PacientHealthInsurance;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import com.br.clinicregistersystem.service.PacientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        pacient.setPersonLastRegisterDate(OffsetDateTime.now());
        return pacientService.savePacient(pacient);
    }


//    Put a pacient on database
    @PutMapping("/{personId}")
    public ResponseEntity<Pacient> updatePacient (@Valid @PathVariable Long personId, @RequestBody Pacient pacient) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacient.setPersonId(personId);
        pacient = pacientService.updatePacients(pacient);
        return ResponseEntity.ok(pacient);
    }

//    Inactive a pacient on database
    @PutMapping("/{personId}/inactive")
    public ResponseEntity<Void> inactivePacient (@Valid @PathVariable Long personId) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacientService.inactivePacientById(personId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{personId}/active")
    public ResponseEntity<Void> activePacient (@Valid @PathVariable Long personId) {
        if (!pacientRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacientService.activePacientById(personId);
        return ResponseEntity.noContent().build();
    }




//    ----------------------------------------------------- PRONTUÁRIO



    @GetMapping("/{personId}/prontuary")
    public ResponseEntity<PersonPacientProntuary> searchProntuary(@PathVariable Long personId) {

        return prontuaryRepository.findById(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{personId}/prontuary")
    public ResponseEntity<PersonPacientProntuary> updateProntuary (@Valid @PathVariable Long personId,
                                                                   @RequestBody PersonPacientProntuary personPacientProntuary) {
        if (!prontuaryRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        personPacientProntuary.setProntuaryId(personId);
        personPacientProntuary = pacientService.updatePacientProntuary(personPacientProntuary);

        return ResponseEntity.ok(personPacientProntuary);
    }




//    ----------------------------------------------------- PLANO DE SAÚDE


    private HealthInsuranceRepository healthInsuranceRepository;


    @GetMapping("/{personId}/health-insurance")
    public ResponseEntity<PacientHealthInsurance> searchHealthInsurance(@PathVariable Long personId) {

        return healthInsuranceRepository.findById(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{personId}/health-insurance")
    public ResponseEntity<PacientHealthInsurance> updateHealthInsurance (@Valid @PathVariable Long personId,
                                                                   @RequestBody PacientHealthInsurance pacientHealthInsurance) {
        if (!healthInsuranceRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacientHealthInsurance.setPacientHealthInsuranceId(personId);
        pacientHealthInsurance = pacientService.updateHealthInsurance(pacientHealthInsurance);

        return ResponseEntity.ok(pacientHealthInsurance);
    }



}
