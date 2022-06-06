package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.ProntuaryRepository;
import com.br.clinicregistersystem.model.PacientProntuary;
import com.br.clinicregistersystem.service.ProntuaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacients/{personId}/prontuary")
@AllArgsConstructor
public class ProntuaryController {

    private ProntuaryRepository prontuaryRepository;
    private ProntuaryService prontuaryService;


    /**(PUT) Find prontuary by person ID.*/
    @GetMapping
    public PacientProntuary searchProntuary(@PathVariable Long personId) {
        return prontuaryService.searchByProntuaryId(personId);
    }


    /**(PUT) Update the pacient's prontuary.*/
    @Transactional
    @PutMapping
    public ResponseEntity<PacientProntuary> updateProntuary (@Valid @PathVariable Long personId,
                                                             @RequestBody PacientProntuary pacientProntuary) {
        if (!prontuaryRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        pacientProntuary.setProntuaryId(personId);
        pacientProntuary = prontuaryService.updatePacientProntuary(pacientProntuary);
        return ResponseEntity.ok(pacientProntuary);
    }

}
