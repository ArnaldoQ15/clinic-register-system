package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientChildRepository;
import com.br.clinicregistersystem.dto.PersonPacientChildInDto;
import com.br.clinicregistersystem.dto.PersonPacientChildOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientChild;
import com.br.clinicregistersystem.util.statics.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
            throw new NotFoundException(ExceptionMessage.CHILD_NOT_FOUND);

        List<PersonPacientChildOutDto> childrenOutDtoList = new ArrayList<>();
        children.forEach(personPacientChild -> childrenOutDtoList.add(modelMapper.map(personPacientChild,
                PersonPacientChildOutDto.class)));

        return childrenOutDtoList;
    }


    /**Update pacient's child information.*/
    public ResponseEntity<PersonPacientChildOutDto> update(PersonPacientChildInDto dto) {
        Optional<PersonPacientChild> childOptional = repository.findById(dto.getChildId());
        if (childOptional.isEmpty())
            throw new NotFoundException(ExceptionMessage.CHILD_NOT_FOUND);

        childOptional.get().setLastUpdate(OffsetDateTime.now());
        childOptional.get().setChildName(dto.getChildName());
        childOptional.get().setChildSex(dto.getChildSex());
        childOptional.get().setChildBirthday(dto.getChildBirthday());
        childOptional.get().setChildAge(personService.insertAge(childOptional.get().getChildBirthday()));
        repository.save(childOptional.get());

        return ResponseEntity.ok().build();
    }


    /**Convert ChildInDto to entity.*/
    public PersonPacientChild convertToEntity(PersonPacientChildInDto dto, PersonPacient pacient) {
        PersonPacientChild child = new PersonPacientChild();
        BeanUtils.copyProperties(dto, child);
        child.setResponsable(pacient.getPersonName());
        child.setRegisterDate(OffsetDateTime.now());
        child.setChildAge(personService.insertAge(child.getChildBirthday()));
        child.setPacient(pacient);
        return child;
    }

}
