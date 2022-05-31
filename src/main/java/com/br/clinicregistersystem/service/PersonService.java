package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Person;

public class PersonService {

    private PersonRepository personRepository;

//    Find by Person ID
    public Person searchByPersonId(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Pacient with Person ID not found."));
    }

}
