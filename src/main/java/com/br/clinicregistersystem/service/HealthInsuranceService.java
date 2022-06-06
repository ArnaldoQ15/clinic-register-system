package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientHealthInsuranceRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class HealthInsuranceService {

    private PersonPacientHealthInsuranceRepository personPacientHealthInsuranceRepository;


    /**Find the pacient's health insurance.*/
    public PersonPacientHealthInsurance pacientHealthInsurance(Long personId) {
        return personPacientHealthInsuranceRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient's health insurance not found."));
    }


    /**Update the pacient's health insurance.*/
    public PersonPacientHealthInsurance updateHealthInsurance(PersonPacientHealthInsurance personPacientHealthInsurance) {
        personPacientHealthInsurance.setLastRegister(OffsetDateTime.now());
        return personPacientHealthInsuranceRepository.save(personPacientHealthInsurance);
    }

}
