package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PersonPacient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PacientService {

    private PersonPacientRepository personPacientRepository;


    /**Find a pacient by Person ID.*/
    public PersonPacient searchByPersonId(Long personId) {
        return personPacientRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient not found."));
    }


    /**Save pacient on repository.*/
    @Transactional
    public PersonPacient savePacient (PersonPacient personPacient) {
        personPacient.setPersonStatus(true);
        addPacientAge(personPacient);
        personPacient.getHealthInsurance().setLastRegister(OffsetDateTime.now());
        personPacient.getProntuary().setLastRegisterDate(OffsetDateTime.now());
        return personPacientRepository.save(personPacient);
    }


    /**Set pacient's age on database.*/
    public void addPacientAge (PersonPacient personPacient) {
        Period periodAge = Period.between(personPacient.getPersonBirthday(), LocalDate.now());
        Integer realPacientAge = Math.abs(periodAge.getYears());
        personPacient.setPersonAge(realPacientAge);
    }


    /**Update pacient's age method.*/
    public void makeBirthday(PersonPacient personPacient) {
        Integer actualAge = personPacient.getPersonAge();
        int month = personPacient.getPersonBirthday().getMonthValue();
        int day = personPacient.getPersonBirthday().getDayOfMonth();
        int thatMonth = LocalDate.now().getMonthValue();
        int thatDay = LocalDate.now().getDayOfMonth();

        if ((day == thatDay) && (month == thatMonth)) {
            actualAge = actualAge + 1;
        }

        personPacient.setPersonAge(actualAge);
    }


    /**Validate if a person exists on database.*/
    public void validatePersonExists(PersonPacient personPacient) {
        Optional<PersonPacient> personCpf = personPacientRepository.findByPersonDocumentCpf(personPacient.getPersonDocumentCpf());
        if (personCpf.isPresent())
            throw new BusinessException("There is already a pacient registered with this CPF.");

        Optional<PersonPacient> personEmail = personPacientRepository.findByPersonEmail(personPacient.getPersonEmail());
        if (personEmail.isPresent())
            throw new BusinessException("There is already a pacient registered with this e-mail.");
    }


    /**Update pacient method.*/
    public PersonPacient updatePacients (PersonPacient personPacient) {
        personPacient.setPersonStatus(true);
        personPacient.setPersonLastRegisterDate(OffsetDateTime.now());

        validatePersonExists(personPacient);

        return personPacientRepository.save(personPacient);
    }


    /**Inactive/active (if was inactive) a pacient by Person ID.*/
    @Transactional
    public void changeStatusPacient(Long personId) {
        PersonPacient personPacient = this.searchByPersonId(personId);
        personPacient.setPersonStatus(!personPacient.getPersonStatus());
        personPacientRepository.save(personPacient);
    }

}
