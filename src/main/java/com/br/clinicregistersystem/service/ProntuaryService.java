package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ProntuaryRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PacientProntuary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ProntuaryService {

    private ProntuaryRepository prontuaryRepository;


    /**Find the pacient's prontuary.*/
    public PacientProntuary searchByProntuaryId(Long personId) {
        return prontuaryRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Prontuary not found."));
    }


    /**Update the pacient's prontuary.*/
    public PacientProntuary updatePacientProntuary(PacientProntuary pacientProntuary) {
        pacientProntuary.setLastRegisterDate(OffsetDateTime.now());
        return prontuaryRepository.save(pacientProntuary);
    }

}
