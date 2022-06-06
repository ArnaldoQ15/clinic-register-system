package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.DoctorDto;
import com.br.clinicregistersystem.dto.DoctorInformationDto;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.service.DoctorHourService;
import com.br.clinicregistersystem.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private PersonDoctorRepository personDoctorRepository;
    private DoctorService doctorService;
    private DoctorHourService doctorHourService;
    private DoctorInformationDto doctorInformationDto;


    /**(GET) Find all doctors on database.*/
    @GetMapping
    public List<DoctorDto> searchAllDoctors() {
        List<PersonDoctor> personDoctors = personDoctorRepository.findAll();
        return doctorService.convertListToDto(personDoctors);
    }


    /**(GET) Find all doctor professional information.*/
    @GetMapping("/info")
    public List<DoctorInformationDto> searchAllDoctorInformations() {
        List<PersonDoctor> personDoctors = personDoctorRepository.findAll();
        return doctorService.convertToDto(personDoctors);
    }


    /**(GET) Find doctor by Person ID.*/
    @GetMapping("/{personId}")
    public PersonDoctor searchDoctorById(@PathVariable Long personId) {
        return doctorService.searchByPersonId(personId);
    }


    /**(POST) Add new doctor on database.*/
    @Transactional
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto addDoctor(@Valid @RequestBody PersonDoctor personDoctor) {
        personDoctor.setPersonLastRegisterDate(OffsetDateTime.now());
        doctorService.validatePersonExists(personDoctor);
        doctorHourService.createDoctorAgenda(personDoctor);
        return doctorService.saveDoctor(personDoctor);
    }


    /**(PUT) Update a doctor on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDoctor> updateDoctor(@Valid @PathVariable Long personId, @RequestBody PersonDoctor personDoctor) {
        if (!personDoctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        personDoctor.setPersonId(personId);
        personDoctor = doctorService.updateDoctor(personDoctor);
        return ResponseEntity.ok(personDoctor);
    }


    /**(PUT) Active/inactive a doctor.*/
    @PutMapping("/{personId}/status")
    public ResponseEntity<Void> activeStatusDoctor (@Valid @PathVariable Long personId) {
        if (!personDoctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        doctorService.changeStatusDoctor(personId);
        return ResponseEntity.noContent().build();
    }


    /**(PUT) Renew register of a doctor.*/
    @PutMapping("/{personId}/renew-crm")
    public ResponseEntity<Void> renewDoctorCrm (@Valid @PathVariable Long personId) {
        if (!personDoctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        doctorService.renewValidity(personId);
        return ResponseEntity.noContent().build();
    }

}
