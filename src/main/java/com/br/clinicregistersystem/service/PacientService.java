package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PacientService {

    private PacientRepository pacientRepository;
    private PersonRepository personRepository;


//    Find by Pacient ID
    public Pacient searchByPacientId(Long pacientId) {
        return pacientRepository.findById(pacientId)
                .orElseThrow(() -> new BusinessException("Pacient ID not found."));
    }



//    Save pacient on repository
    @Transactional
    public Pacient savePacient (Pacient pacient) {
        Boolean cpfInUse = personRepository.findByDocumentCpf(pacient.getPersonDocumentCpf())
                .stream()
                .anyMatch(personExist -> !personExist.equals(pacient));

        if (cpfInUse) {
            throw new BusinessException("There is already a person registered with this CPF.");
        }
        return pacientRepository.save(pacient);
    }



//    Delete a pacient by Pacient ID
    @Transactional
    public void deletePacientById(Long pacientId) {
        pacientRepository.deleteById(pacientId);
    }

}
