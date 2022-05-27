package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.ConsultsRepository;
import com.br.clinicregister.model.ConsultStatus;
import com.br.clinicregister.model.Consults;
import com.br.clinicregister.model.Person;
import com.br.clinicregister.model.PersonPacient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ConsultRequestService {

    private PersonService personService;
    private ConsultsRepository consultsRepository;

    @Transactional
    public Consults request(Consults consults) {
        Person person = personService.seachPersonId(consults.getPersonPacient().getId());

        consults.setPersonPacient((PersonPacient) person);
        consults.setStatus(ConsultStatus.PENDING);
        consults.setRegisterDate(OffsetDateTime.now());

        return consultsRepository.save(consults);
    }

}
