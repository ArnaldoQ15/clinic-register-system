package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.PacientRepository;
import com.br.clinicregister.exception.BusinessException;
import com.br.clinicregister.model.PersonPacient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PacientService {

    private PacientRepository pacientRepository;

    public PersonPacient searchPacient(Long pacientId) {
        return pacientRepository.findById(pacientId)
                .orElseThrow(() -> new BusinessException("Pacient not found."));
    }

    @Transactional
    public PersonPacient savePacient(PersonPacient personPacient) {

        Boolean registerExists = pacientRepository.registerExists(personPacient.getEmail())
                .stream()
                .anyMatch(personPacientExist -> !personPacientExist.equals(personPacient));

        if (registerExists) {
            throw new BusinessException("There is already a pacient registered with this CPF");
        }
        return pacientRepository.save(personPacient);
    }

    @Transactional
    public void deletePacient(Long pacientId) {
        pacientRepository.deleteById(pacientId);
    }
}
