package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.HealthInsuranceRepository;
import com.br.clinicregistersystem.model.PacientHealthInsurance;
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

    private HealthInsuranceRepository healthInsuranceRepository;
    private HealthInsuranceService healthInsuranceService;



//    (PUT) Find health insurance by person ID
    @GetMapping
    public PacientHealthInsurance searchHealthInsurance(@PathVariable Long personId) {
        return healthInsuranceService.pacientHealthInsurance(personId);
    }



//    (PUT) Update the pacient's health insurance
    @Transactional
    @PutMapping
    public ResponseEntity<PacientHealthInsurance> updateHealthInsurance (@Valid @PathVariable Long personId,
                                                                         @RequestBody PacientHealthInsurance pacientHealthInsurance) {
        if (!healthInsuranceRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        pacientHealthInsurance.setPacientHealthInsuranceId(personId);
        pacientHealthInsurance = healthInsuranceService.updateHealthInsurance(pacientHealthInsurance);
        return ResponseEntity.ok(pacientHealthInsurance);
    }

}
