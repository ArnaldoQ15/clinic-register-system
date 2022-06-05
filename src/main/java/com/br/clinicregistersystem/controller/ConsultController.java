package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.dto.ConsultDto;
import com.br.clinicregistersystem.model.*;
import com.br.clinicregistersystem.service.ConsultService;
import com.br.clinicregistersystem.service.DoctorHourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ConsultController {

    private ConsultRepository consultRepository;
    private ConsultService consultService;



//    (GET) Find all consults
    @GetMapping("/consults")
    public List<Consult> searchAllConsults() {
        return consultRepository.findAll();
    }



    /**(GET) Find consults by Person ID.*/
    @GetMapping("/pacients/{personId}/consults")
    public Consult searchConsultById(@PathVariable Long personId) {
        return consultService.searchByPersonId(personId);
    }


    /**(POST) New consult on database.*/
    @Transactional
    @PostMapping("/pacients/{personId}/consults/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Consult addConsult(@RequestBody Consult consult, @Valid @PathVariable Long personId) {

        return consultService.saveConsult(consult, personId);
    }

}
