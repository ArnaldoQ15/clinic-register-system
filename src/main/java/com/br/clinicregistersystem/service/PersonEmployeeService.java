package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonEmployeeRepository;
import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@AllArgsConstructor
@Service
public class PersonEmployeeService {

    @Autowired
    private PersonEmployeeRepository repository;

    @Autowired
    private PersonService personService;


//    /**Save a employee on database.*/
//    public ResponseEntity<PersonEmployeeOutDto> persist(PersonEmployeeInDto dto) {
//        personService.validateCpfExists(dto.getPersonDocumentCpf());
//        personService.validateEmailExists(dto.getPersonEmail());
//    }

}
