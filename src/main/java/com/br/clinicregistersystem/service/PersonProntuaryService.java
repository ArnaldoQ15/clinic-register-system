package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonProntuaryRepository;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryInDto;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryOutDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonProntuaryService {

    @Autowired
    private PersonProntuaryRepository repository;

    @Autowired
    private PersonPacientRepository pacientRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**Find the pacient's prontuary.*/
    public List<PersonPacientProntuaryOutDto> findAllById(Long personId) {
        List<PersonPacientProntuary> prontuaries = repository.findByPersonId(personId);
        if (prontuaries.isEmpty())
            throw new BusinessException("Prontuary not found.");

        List<PersonPacientProntuaryOutDto> prontuaryOutDtoList = new ArrayList<>();
        prontuaries.forEach(personPacientProntuary -> prontuaryOutDtoList.add(modelMapper.map(personPacientProntuary,
                PersonPacientProntuaryOutDto.class)));

        return prontuaryOutDtoList;
    }


    /**Update pacient's prontuary.*/
    public ResponseEntity<PersonPacientProntuaryOutDto> update(PersonPacientProntuaryInDto dto) {
        Optional<PersonPacientProntuary> prontuaryOptional = repository.findById(dto.getProntuaryId());
        if (prontuaryOptional.isEmpty())
            throw new BusinessException("Prontuary not found.");

        prontuaryOptional.get().setLastUpdate(OffsetDateTime.now());
        prontuaryOptional.get().setSymptoms(dto.getSymptoms());
        repository.save(prontuaryOptional.get());

        return ResponseEntity.ok().build();
    }


    /**Add new pacient's prontuary.*/
    public ResponseEntity<PersonPacientProntuaryOutDto> persist(Long personId, PersonPacientProntuaryInDto dto) {
        Optional<PersonPacient> pacient = pacientRepository.findById(personId);
        if (pacient.isEmpty())
            throw new BusinessException("Pacient not found.");

        PersonPacientProntuary entityNew = new PersonPacientProntuary();

//        entityNew.setPacient(pacient.get());
        entityNew.setFirstTime(false);
        entityNew.setSymptoms(dto.getSymptoms());
        entityNew.setPersonId(pacient.get().getPersonId());
        entityNew.setRegisterDate(OffsetDateTime.now());

        repository.save(entityNew);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
