package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonProntuaryRepository;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryInDto;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonProntuaryService {

    @Autowired
    private PersonProntuaryRepository repository;

    @Autowired
    private PersonPacientRepository pacientRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**Add new pacient's prontuary.*/
    public ResponseEntity<PersonPacientProntuaryOutDto> persist(Long personId, PersonPacientProntuaryInDto dto) {
        Optional<PersonPacient> pacient = pacientRepository.findById(personId);
        if (pacient.isPresent()) {
            PersonPacientProntuary entityNew = modelMapper.map(dto, PersonPacientProntuary.class);
            entityNew.setRegisterDate(OffsetDateTime.now());
            entityNew.setPacient(pacient.get());

            repository.save(entityNew);

            pacient.get().getProntuaries().add(entityNew);
            pacientRepository.saveAndFlush(pacient.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new NotFoundException("Pacient not found.");
    }


    /**Find the pacient's prontuary.*/
    public List<PersonPacientProntuaryOutDto> findAllById(Long personId) {
        List<PersonPacientProntuary> prontuaries = repository.findByPersonId(personId);
        if (!prontuaries.isEmpty()) {
            List<PersonPacientProntuaryOutDto> prontuaryOutDtoList = new ArrayList<>();

            prontuaries.forEach(personPacientProntuary -> prontuaryOutDtoList.add(modelMapper.
                    map(personPacientProntuary, PersonPacientProntuaryOutDto.class)));

            return prontuaryOutDtoList;
        }
        throw new NotFoundException("Prontuary not found.");
    }


    /**Update pacient's prontuary.*/
    public ResponseEntity<PersonPacientProntuaryOutDto> update(PersonPacientProntuaryInDto dto) {
        Optional<PersonPacientProntuary> prontuaryOptional = repository.findById(dto.getProntuaryId());
        if (prontuaryOptional.isEmpty())
            throw new NotFoundException("Prontuary not found.");

        prontuaryOptional.get().setLastUpdate(OffsetDateTime.now());
        prontuaryOptional.get().setSymptoms(dto.getSymptoms());
        repository.save(prontuaryOptional.get());

        return ResponseEntity.ok().build();
    }


    public PersonPacientProntuary convertToEntity(PersonPacientProntuaryInDto dto, PersonPacient pacient) {
        PersonPacientProntuary prontuary = modelMapper.map(dto, PersonPacientProntuary.class);
        prontuary.setRegisterDate(OffsetDateTime.now());
        prontuary.setPacient(pacient);
        return prontuary;
    }

}