package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.HealthInsuranceRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PacientHealthInsurance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class HealthInsuranceService {

    private HealthInsuranceRepository healthInsuranceRepository;


    /**Find the pacient's health insurance.*/
    public PacientHealthInsurance pacientHealthInsurance(Long personId) {
        return healthInsuranceRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient's health insurance not found."));
    }


    /**Update the pacient's health insurance.*/
    public PacientHealthInsurance updateHealthInsurance(PacientHealthInsurance pacientHealthInsurance) {
        pacientHealthInsurance.setLastRegister(OffsetDateTime.now());
        return healthInsuranceRepository.save(pacientHealthInsurance);
    }

}
