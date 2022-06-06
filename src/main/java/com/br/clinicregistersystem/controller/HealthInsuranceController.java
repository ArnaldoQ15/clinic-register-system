package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.PersonPacientHealthInsuranceRepository;
import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import com.br.clinicregistersystem.service.HealthInsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/pacients/{personId}/health-insurance")
public class HealthInsuranceController {

    private PersonPacientHealthInsuranceRepository personPacientHealthInsuranceRepository;
    private HealthInsuranceService healthInsuranceService;


    /**(PUT) Find health insurance by person ID.*/
    @GetMapping
    public PersonPacientHealthInsurance searchHealthInsurance(@PathVariable Long personId) {
        return healthInsuranceService.pacientHealthInsurance(personId);
    }


    /**(PUT) Update the pacient's health insurance.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PersonPacientHealthInsurance> updateHealthInsurance (@Valid @PathVariable Long personId,
                                                                               @RequestBody PersonPacientHealthInsurance personPacientHealthInsurance) {
        if (!personPacientHealthInsuranceRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        personPacientHealthInsurance.setPacientHealthInsuranceId(personId);
        personPacientHealthInsurance = healthInsuranceService.updateHealthInsurance(personPacientHealthInsurance);
        return ResponseEntity.ok(personPacientHealthInsurance);
    }

}
