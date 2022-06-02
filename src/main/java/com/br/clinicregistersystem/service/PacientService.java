package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.HealthInsuranceRepository;
import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.domain.repository.ProntuaryRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.model.PacientHealthInsurance;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class PacientService {

    private PacientRepository pacientRepository;
    private PersonRepository personRepository;


//    Find by Person ID
    public Pacient searchByPersonId(Long personId) {
        return pacientRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Person ID not found."));
    }



//    Save pacient on repository
    @Transactional
    public Pacient savePacient (Pacient pacient) {

        pacient.setPersonStatus(true);

        if (pacient.getHealthInsurance().getLastRegister() == null)
            pacient.getHealthInsurance().setLastRegister(OffsetDateTime.now());

        if (pacient.getProntuary().getLastRegisterDate() == null)
            pacient.getProntuary().setLastRegisterDate(OffsetDateTime.now());

        Boolean cpfInUse = pacientRepository.findByPersonDocumentCpf(pacient.getPersonDocumentCpf())
                .stream()
                .anyMatch(cpfPacientExist -> !cpfPacientExist.equals(pacient));

        Boolean emailInUse = pacientRepository.findByPersonEmail(pacient.getPersonEmail())
                .stream()
                .anyMatch(pacientExist -> !pacientExist.equals(pacient));

        if (emailInUse) {
            throw new BusinessException("There is already a pacient registered with this e-mail.");
        } else if (cpfInUse) {
            throw new BusinessException("There is already a pacient registered with this CPF.");
        }

        return pacientRepository.save(pacient);
    }

    public Pacient updatePacients (Pacient pacient) {

        OffsetDateTime registerDatePront = pacient.getPersonLastRegisterDate();

        if (registerDatePront == null) {
            registerDatePront = OffsetDateTime.now();
        }
        else {
            registerDatePront = pacient.getPersonLastRegisterDate();
        }

        pacient.setPersonLastRegisterDate(registerDatePront);


        Boolean cpfInUsePacientId = pacientRepository.findByPersonDocumentCpf(pacient.getPersonDocumentCpf())
                .stream()
                .anyMatch(cpfPacientExist -> !cpfPacientExist.equals(pacient.getPersonEmail()));

        Boolean emailInUsePacientId = pacientRepository.findByPersonEmail(pacient.getPersonEmail())
                .stream()
                .anyMatch(pacientExist -> !pacientExist.equals(pacient.getPersonEmail()));


        pacient.setPersonStatus(true);
        return pacientRepository.save(pacient);
    }


//    Inactive a pacient by Person ID
    @Transactional
    public void inactivePacientById(Long personId) {
        Pacient pacient = this.searchByPersonId(personId);
        pacient.inactiveStatusPacient();
        pacientRepository.save(pacient);
    }

//    Active a pacient (if was inactive) by Person ID
    @Transactional
    public void activePacientById(Long personId) {
        Pacient pacient = this.searchByPersonId(personId);
        pacient.activeStatusPacient();
        pacientRepository.save(pacient);
    }





//    ----------------------------------------------------- PRONTUÁRIO


    private ProntuaryRepository prontuaryRepository;


    public PersonPacientProntuary searchByProntuaryId(Long personId) {
        return prontuaryRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Prontuary not found."));
    }

    public PersonPacientProntuary updatePacientProntuary(PersonPacientProntuary personPacientProntuary) {
        personPacientProntuary.setLastRegisterDate(OffsetDateTime.now());
        return prontuaryRepository.save(personPacientProntuary);
    }





//    ----------------------------------------------------- PLANO DE SAÚDE


    private HealthInsuranceRepository healthInsuranceRepository;


    public PacientHealthInsurance pacientHealthInsurance(Long personId) {
        return healthInsuranceRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient's health insurance not found."));
    }

    public PacientHealthInsurance updateHealthInsurance(PacientHealthInsurance pacientHealthInsurance) {
        pacientHealthInsurance.setLastRegister(OffsetDateTime.now());
        return healthInsuranceRepository.save(pacientHealthInsurance);
    }


}
