package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.ConsultRepository;
import com.br.clinicregister.model.Consult;
import com.br.clinicregister.model.ConsultStatus;
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
    private ConsultRepository consultRepository;

    @Transactional
    public Consult request(Consult consult) {
        Person person = personService.seachPersonId(consult.getPersonPacient().getId());

        consult.setPersonPacient((PersonPacient) person);
        consult.setStatus(ConsultStatus.PENDING);
        consult.setRegisterDate(OffsetDateTime.now());

        return consultRepository.save(consult);
    }

}