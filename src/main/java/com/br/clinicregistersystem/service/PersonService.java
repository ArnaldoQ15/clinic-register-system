package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonService {

    private PersonRepository personRepository;

    private Person person;

//    Find by Person ID
    public Person searchByPersonId(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient with Person ID not found."));
    }

}
