package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.PersonRepository;
import com.br.clinicregister.exception.BusinessException;
import com.br.clinicregister.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;

    public Person seachPersonId(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Person not found."));
    }

}
