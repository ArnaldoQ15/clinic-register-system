package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Pacient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
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
        pacient.getHealthInsurance().setLastRegister(OffsetDateTime.now());
        pacient.getProntuary().setLastRegisterDate(OffsetDateTime.now());
        return pacientRepository.save(pacient);
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
