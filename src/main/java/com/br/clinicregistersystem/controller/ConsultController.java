package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.ConsultOutDto;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.service.ConsultService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ConsultController {

    @Autowired
    private ConsultService service;


    /**(GET) Find all consults.*/
    @GetMapping("/consults")
    public List<ConsultOutDto> findAll() {
        return service.findAll();
    }


    /**(GET) Find consults by Person ID.*/
    @GetMapping("/{personId}/consults")
    public List<ConsultOutDto> findId(@PathVariable Long personId) {
        return service.findId(personId);
    }


    /**(POST) New consult on database.*/
    @Transactional
    @PostMapping("/{personId}/consults/new")
    public ResponseEntity<Consult> addConsult(@RequestBody ConsultInDto dto, @Valid @PathVariable Long personId) {
        return service.persist(dto, personId);
    }

}
