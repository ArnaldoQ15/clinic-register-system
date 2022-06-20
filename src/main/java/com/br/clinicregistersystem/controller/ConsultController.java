package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.ConsultOutDto;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsultController {

    @Autowired
    private ConsultService service;


    /**(GET) Find all consults.*/
    @GetMapping("/consults")
    public ResponseEntity<List<ConsultOutDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    /**(GET) Find consults by Person ID.*/
    @GetMapping("/{personId}/consults")
    public ResponseEntity<List<ConsultOutDto>> findId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findId(personId));
    }


    /**(POST) New consult on database.*/
    @Transactional
    @PostMapping("/{personId}/consults/new")
    public ResponseEntity<Consult> addConsult(@RequestBody ConsultInDto dto, @Valid @PathVariable Long personId) {
        return service.persist(dto, personId);
    }


    /**(DELETE) Cancel a consult (by pacient).*/
    @Transactional
    @DeleteMapping("/consults/{consultId}/delete")
    public ResponseEntity<Void> deleteByPacient(@Valid @PathVariable Long consultId) {
        service.deleteByPacient(consultId);
        return ResponseEntity.noContent().build();
    }


    /**(DELETE) Cancel a consult (by clinic).*/
    @Transactional
    @DeleteMapping("/consults/{consultId}/delete-admin")
    public ResponseEntity<Void> deleteByClinic(@Valid @PathVariable Long consultId) {
        service.deleteByClinic(consultId);
        return ResponseEntity.noContent().build();
    }

}
