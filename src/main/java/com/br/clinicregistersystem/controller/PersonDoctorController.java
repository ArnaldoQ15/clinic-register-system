package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonDoctorAgendaDto;
import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorInformationDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.service.PersonDoctorAgendaService;
import com.br.clinicregistersystem.service.PersonDoctorService;
import com.br.clinicregistersystem.util.enums.DayWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class PersonDoctorController {

    @Autowired
    private PersonDoctorService service;

    @Autowired
    private PersonDoctorAgendaService personDoctorAgendaService;


    /**(GET) Find all doctors on database.*/
    @GetMapping
    public ResponseEntity<List<PersonDoctorOutDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    /**(GET) Find all doctor professional information.*/
    @GetMapping("/info")
    public ResponseEntity<List<PersonDoctorInformationDto>> findAllDoctorInformation() {
        return ResponseEntity.ok().body(service.convertToInfo());
    }


    /**(GET) Find doctor by Person ID.*/
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDoctorOutDto> findId(@PathVariable @Valid Long personId) {
        return ResponseEntity.ok().body(service.findId(personId));
    }


    /**(GET) Find all doctor agenda by Person ID.*/
    @GetMapping("/{personId}/agenda")
    public ResponseEntity<List<PersonDoctorAgendaDto>> findAgendaById(@Valid @PathVariable Long personId) {
        return ResponseEntity.ok().body(personDoctorAgendaService.findId(personId));
    }


    /**(GET) Find a doctor agenda day by Person ID.*/
    @GetMapping("/{personId}/agenda/{dayWeek}")
    public ResponseEntity<PersonDoctorAgendaDto> findAgendaDayById(@Valid @PathVariable Long personId,
                                                                   @PathVariable DayWeek dayWeek) {
        return ResponseEntity.ok().body(personDoctorAgendaService.findAgendaDayById(personId, dayWeek));
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


    /**(PUT) Renew register of a doctor.*/
    @Transactional
    @PutMapping("/{personId}/renew-crm")
    public ResponseEntity<Void> renewCrm(@Valid @PathVariable Long personId) {
        service.renewValidity(personId);
        return ResponseEntity.noContent().build();
    }


    /**(DELETE) Active/inactive a doctor.*/
    @Transactional
    @DeleteMapping("/{personId}/delete")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long personId) {
        service.delete(personId);
        return ResponseEntity.noContent().build();
    }

}
