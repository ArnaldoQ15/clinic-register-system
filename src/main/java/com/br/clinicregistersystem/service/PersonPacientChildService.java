package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientChildRepository;
import com.br.clinicregistersystem.dto.PersonPacientChildInDto;
import com.br.clinicregistersystem.dto.PersonPacientChildOutDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PersonPacientChild;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonPacientChildService {

    @Autowired
    private PersonPacientChildRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;


    /**Find the pacient's child.*/
    public List<PersonPacientChildOutDto> findAllById(Long personId) {
        List<PersonPacientChild> children = repository.findByPersonId(personId);
        if (children.isEmpty())
            throw new BusinessException("Child not found.");

        List<PersonPacientChildOutDto> childrenOutDtoList = new ArrayList<>();
        children.forEach(personPacientChild -> childrenOutDtoList.add(modelMapper.map(personPacientChild,
                PersonPacientChildOutDto.class)));

        return childrenOutDtoList;
    }


    /**Update pacient's child information.*/
    public ResponseEntity<PersonPacientChildOutDto> update(PersonPacientChildInDto dto) {
        Optional<PersonPacientChild> childOptional = repository.findById(dto.getChildId());
        if (childOptional.isEmpty())
            throw new BusinessException("Child not found.");

        childOptional.get().setLastUpdate(OffsetDateTime.now());
        childOptional.get().setChildName(dto.getChildName());
        childOptional.get().setChildSex(dto.getChildSex());
        childOptional.get().setChildBirthday(dto.getChildBirthday());
        childOptional.get().setChildAge(personService.insertAge(childOptional.get().getChildBirthday()));
        repository.save(childOptional.get());

        return ResponseEntity.ok().build();
    }

}
