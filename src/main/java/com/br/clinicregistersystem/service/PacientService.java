package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
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



//    Find by Person ID
    public Pacient searchByPersonId(Long personId) {
        return pacientRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient not found."));
    }



//    Save pacient on repository
    @Transactional
    public Pacient savePacient (Pacient pacient) {
        pacient.setPersonStatus(true);
        pacient.getHealthInsurance().setLastRegister(OffsetDateTime.now());
        pacient.getProntuary().setLastRegisterDate(OffsetDateTime.now());

        Boolean cpfInUse = pacientRepository.findByPersonDocumentCpf(pacient.getPersonDocumentCpf())
                .stream()
                .anyMatch(cpfPacientExist -> !cpfPacientExist.equals(pacient));

        Boolean emailInUse = pacientRepository.findByPersonEmail(pacient.getPersonEmail())
                .stream()
                .anyMatch(emailPacientExist -> !emailPacientExist.equals(pacient));

        if (emailInUse) {
            throw new BusinessException("There is already a pacient registered with this e-mail.");
        } else if (cpfInUse) {
            throw new BusinessException("There is already a pacient registered with this CPF.");
        }

        return pacientRepository.save(pacient);
    }



    public Pacient updatePacients (Pacient pacient) {
        pacient.setPersonStatus(true);
        pacient.setPersonLastRegisterDate(OffsetDateTime.now());

        return pacientRepository.save(pacient);
    }



//     Inactive/active (if was inactive) a pacient by Person ID
    @Transactional
    public void changeStatusPacient(Long personId) {
        Pacient pacient = this.searchByPersonId(personId);
        pacient.setPersonStatus(!pacient.getPersonStatus());
        pacientRepository.save(pacient);
    }

}
