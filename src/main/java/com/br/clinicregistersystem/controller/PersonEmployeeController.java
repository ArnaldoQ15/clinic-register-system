package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import com.br.clinicregistersystem.model.PersonEmployee;
import com.br.clinicregistersystem.service.PersonEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class PersonEmployeeController {

    @Autowired
    private PersonEmployeeService service;


    /**(GET) Find all employees on database.*/
    @GetMapping
    public ResponseEntity<List<PersonEmployeeOutDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    /**(GET) Find employee by Person ID.*/
    @GetMapping("/{personId}")
    public ResponseEntity<PersonEmployeeOutDto> findId(@PathVariable @Valid Long personId) {
        return ResponseEntity.ok().body(service.findId(personId));
    }


    /**(POST) Add new employee on database.*/
    @Transactional
    @PostMapping("/new")
    public ResponseEntity<PersonEmployee> persist(@RequestBody PersonEmployeeInDto dto) {
        return service.persist(dto);
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
