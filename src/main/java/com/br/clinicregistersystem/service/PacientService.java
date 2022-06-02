package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Pacient;
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



//    Inactive a pacient by Pacient ID
    @Transactional
    public Pacient inactivePacientById(Pacient pacient) {
        boolean tricker = true;

        if (pacient.getPersonStatus()) {
            tricker = false;
        }
            pacient.setPersonStatus(tricker);

        return pacientRepository.save(pacient);
    }

}
