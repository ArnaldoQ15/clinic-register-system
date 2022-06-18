package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonEmployee;
import com.br.clinicregistersystem.service.PersonEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class PersonEmployeeController {

    @Autowired
    private PersonEmployeeService service;


    /**(GET) Find all employees on database.*/
    @GetMapping
    public List<PersonEmployeeOutDto> findAll() {
        return service.findAll();
    }


    /**(POST) Add new employee on database.*/
    @PostMapping("/new")
    public ResponseEntity<PersonEmployee> persist(@RequestBody PersonEmployeeInDto dto) {
        return service.persist(dto);
    }


    /**(GET) Find employee by Person ID.*/
    @GetMapping("/{personId}")
    public PersonEmployeeOutDto findId(@PathVariable @Valid Long personId) {
        return service.findId(personId);
    }


    /**(PUT) Update a employee on database.*/
    @Transactional
    @PutMapping("/{personId}")
    public ResponseEntity<PersonEmployee> update(@Valid @PathVariable Long personId, @RequestBody PersonEmployeeInDto dto) {
        return service.update(personId, dto);
    }


    /**(DELETE) Active/inactive a employee.*/
    @Transactional
    @DeleteMapping("/{personId}/delete")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long personId) {
        return service.delete(personId);
    }

}
