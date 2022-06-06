package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Pacient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PacientService {

    private PacientRepository pacientRepository;


    /**Find a pacient by Person ID.*/
    public Pacient searchByPersonId(Long personId) {
        return pacientRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient not found."));
    }


    /**Save pacient on repository.*/
    @Transactional
    public Pacient savePacient (Pacient pacient) {
        pacient.setPersonStatus(true);
        addPacientAge(pacient);
        pacient.getHealthInsurance().setLastRegister(OffsetDateTime.now());
        pacient.getProntuary().setLastRegisterDate(OffsetDateTime.now());
        return pacientRepository.save(pacient);
    }


    /**Set pacient's age on database.*/
    public void addPacientAge (Pacient pacient) {
        Period periodAge = Period.between(pacient.getPersonBirthday(), LocalDate.now());
        Integer realPacientAge = Math.abs(periodAge.getYears());
        pacient.setPersonAge(realPacientAge);
    }


    /**Update pacient's age method.*/
    public void makeBirthday(Pacient pacient) {
        Integer actualAge = pacient.getPersonAge();
        int month = pacient.getPersonBirthday().getMonthValue();
        int day = pacient.getPersonBirthday().getDayOfMonth();
        int thatMonth = LocalDate.now().getMonthValue();
        int thatDay = LocalDate.now().getDayOfMonth();

        if ((day == thatDay) && (month == thatMonth)) {
            actualAge = actualAge + 1;
        }

        pacient.setPersonAge(actualAge);
    }


    /**Validate if a person exists on database.*/
    public void validatePersonExists(Pacient pacient) {
        Optional<Pacient> personCpf = pacientRepository.findByPersonDocumentCpf(pacient.getPersonDocumentCpf());
        if (personCpf.isPresent())
            throw new BusinessException("There is already a pacient registered with this CPF.");

        Optional<Pacient> personEmail = pacientRepository.findByPersonEmail(pacient.getPersonEmail());
        if (personEmail.isPresent())
            throw new BusinessException("There is already a pacient registered with this e-mail.");
    }


    /**Update pacient method.*/
    public Pacient updatePacients (Pacient pacient) {
        pacient.setPersonStatus(true);
        pacient.setPersonLastRegisterDate(OffsetDateTime.now());

        validatePersonExists(pacient);

        return pacientRepository.save(pacient);
    }


    /**Inactive/active (if was inactive) a pacient by Person ID.*/
    @Transactional
    public void changeStatusPacient(Long personId) {
        Pacient pacient = this.searchByPersonId(personId);
        pacient.setPersonStatus(!pacient.getPersonStatus());
        pacientRepository.save(pacient);
    }

}
