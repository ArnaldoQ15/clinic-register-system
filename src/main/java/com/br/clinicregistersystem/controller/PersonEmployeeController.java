package com.br.clinicregistersystem.controller;

import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import com.br.clinicregistersystem.service.PersonEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/persons/{personId}/admin")
public class PersonEmployeeController {

    @Autowired
    private PersonEmployeeService service;


//    public ResponseEntity<PersonEmployeeOutDto> persist(PersonEmployeeInDto dto) {
//        return service.persist(dto);
//    }

}
