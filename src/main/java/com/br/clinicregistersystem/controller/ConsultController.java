package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.dto.ConsultDto;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.Pacient;
import com.br.clinicregistersystem.service.ConsultService;
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
    private ConsultDto consultDto;


    /**(GET) Find all consults.*/
    @GetMapping("/consults")
    public List<ConsultDto> searchAllConsults() {
        List<Consult> consults = consultRepository.findAll();
        return consultDto.convertToDto(consults);
    }


    /**(GET) Find consults by Person ID.*/
    @GetMapping("/pacients/{personId}/consults")
    public List<ConsultDto> searchConsultById(@PathVariable Long personId) {
        List<ConsultDto> personConsults = consultService.searchByPersonId(personId);
        return personConsults;
    }


    /**(POST) New consult on database.*/
    @Transactional
    @PostMapping("/pacients/{personId}/consults/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Consult addConsult(@RequestBody Consult consult, @Valid @PathVariable Long personId) {
        return consultService.saveConsult(consult, personId);
    }

}
