package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.domain.repository.DoctorRepository;
import com.br.clinicregistersystem.dto.DoctorDto;
import com.br.clinicregistersystem.dto.DoctorInformationDto;
import com.br.clinicregistersystem.model.*;
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

    private DoctorRepository doctorRepository;
    private DoctorService doctorService;
    private DoctorHourService doctorHourService;
    private DoctorInformationDto doctorInformationDto;
    private DoctorDto doctorDto;


    /**(GET) Find all doctors on database.*/
    @GetMapping
    public List<DoctorDto> searchAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorDto.convertToDto(doctors);
    }


    /**(GET) Find all doctor professional information.*/
    @GetMapping("/info")
    public List<DoctorInformationDto> searchAllDoctorInformations() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorInformationDto.convertToDto(doctors);
    }


    /**(GET) Find doctor by Person ID.*/
    @GetMapping("/{personId}")
    public Doctor searchDoctorById(@PathVariable Long personId) {
        return doctorService.searchByPersonId(personId);
    }



    /**(POST) Add new doctor on database.*/
    @Transactional
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        doctor.setPersonLastRegisterDate(OffsetDateTime.now());
        doctorService.validatePersonExists(doctor);
        doctorHourService.createDoctorAgenda(doctor);
        return doctorService.saveDoctor(doctor);
    }


    /**(PUT) Update a doctor on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<Doctor> updateDoctor(@Valid @PathVariable Long personId, @RequestBody Doctor doctor) {
        if (!doctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        doctor.setPersonId(personId);
        doctor = doctorService.updateDoctor(doctor);
        return ResponseEntity.ok(doctor);
    }



    /**(PUT) Active/inactive a doctor.*/
    @PutMapping("/{personId}/status")
    public ResponseEntity<Void> activeStatusDoctor (@Valid @PathVariable Long personId) {
        if (!doctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        doctorService.changeStatusDoctor(personId);
        return ResponseEntity.noContent().build();
    }



    /**(PUT) Renew register of a doctor.*/
    @PutMapping("/{personId}/renew-crm")
    public ResponseEntity<Void> renewDoctorCrm (@Valid @PathVariable Long personId) {
        if (!doctorRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }
        doctorService.renewValidity(personId);
        return ResponseEntity.noContent().build();
    }

}
