package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorInformationDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.service.PersonDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/doctors")
public class PersonDoctorController {

    @Autowired
    private PersonDoctorService service;


    /**(GET) Find all doctors on database.*/
    @GetMapping
    public List<PersonDoctorOutDto> findAll() {
        return service.findAll();
    }


    /**(GET) Find all doctor professional information.*/
    @GetMapping("/info")
    public List<PersonDoctorInformationDto> findAllDoctorInformation() {
        return service.convertToInfo();
    }


    /**(GET) Find doctor by Person ID.*/
    @GetMapping("/{personId}")
    public PersonDoctorOutDto findId(@PathVariable @Valid Long personId) {
        return service.findId(personId);
    }


    /**(POST) Add new doctor on database.*/
    @Transactional
    @PostMapping("/persist")
    public ResponseEntity<PersonDoctorOutDto> persist(@RequestBody PersonDoctorInDto dto) {
        return service.persist(dto);
    }


    /**(PUT) Update a doctor on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDoctor> update(@Valid @PathVariable Long personId, @RequestBody PersonDoctorInDto dto) {
        return service.update(personId, dto);
    }


    /**(DELETE) Active/inactive a doctor.*/
    @DeleteMapping("/{personId}/delete")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long personId) {
        return service.delete(personId);
    }


    /**(PUT) Renew register of a doctor.*/
    @PutMapping("/{personId}/renew-crm")
    public ResponseEntity<Void> renewCrm(@Valid @PathVariable Long personId) {
        return service.renewValidity(personId);
    }

}
