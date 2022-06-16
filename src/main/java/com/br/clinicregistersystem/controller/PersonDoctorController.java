package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorInformationDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.service.PersonDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private PersonDoctorService personDoctorService;


    /**(GET) Find all doctors on database.*/
    @GetMapping
    public List<PersonDoctorOutDto> findAll() {
        return personDoctorService.findAll();
    }


    /**(GET) Find all doctor professional information.*/
    @GetMapping("/info")
    public List<PersonDoctorInformationDto> findAllDoctorInformation() {
        return personDoctorService.convertToInfo();
    }


    /**(GET) Find doctor by Person ID.*/
    @GetMapping("/{personId}")
    public PersonDoctorOutDto findId(@PathVariable @Valid Long personId) {
        return personDoctorService.findId(personId);
    }


    /**(POST) Add new doctor on database.*/
    @Transactional
    @PostMapping("/persist")
    public ResponseEntity<PersonDoctorOutDto> persist(@Valid @RequestBody PersonDoctorInDto dto) {
        personDoctorService.persist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**(PUT) Update a doctor on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDoctorOutDto> updateDoctor(@Valid @PathVariable Long personId, @RequestBody PersonDoctorInDto dto) {
        personDoctorService.update(personId, dto);
        return ResponseEntity.ok().build();
    }


    /**(DELETE) Active/inactive a doctor.*/
    @DeleteMapping("/{personId}/delete")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long personId) {
        personDoctorService.delete(personId);
        return ResponseEntity.noContent().build();
    }


    /**(PUT) Renew register of a doctor.*/
    @PutMapping("/{personId}/renew-crm")
    public ResponseEntity<Void> renewDoctorCrm (@Valid @PathVariable Long personId) {
        personDoctorService.renewValidity(personId);
        return ResponseEntity.noContent().build();
    }

}
