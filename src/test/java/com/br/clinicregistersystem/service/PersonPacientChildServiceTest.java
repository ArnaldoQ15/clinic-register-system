package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientChildRepository;
import com.br.clinicregistersystem.dto.PersonPacientChildInDto;
import com.br.clinicregistersystem.dto.PersonPacientChildOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientChild;
import lombok.Data;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonPacientChildServiceTest {

    @InjectMocks
    private PersonPacientChildService service;

    @Mock
    private PersonPacientChildRepository repository;

    @Mock
    private PersonPacientChild child;

    @Mock
    private PersonPacientChildInDto inDto;

    @Mock
    private PersonPacientChildOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonService personService;

    @Mock
    private PersonPacient pacient;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAllById ok")
    @Test
    void findAllById() {
        List<PersonPacientChild> children = new ArrayList<>();
        children.add(child);

        when(repository.findByPersonId(anyLong())).thenReturn(children);
        service.findAllById(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findAllById NotFoundException")
    @Test
    void findAllByIdNotFoundException() {
        Assert.assertThrows(NotFoundException.class, () -> service.findAllById(anyLong()));
    }


    @DisplayName("update Ok")
    @Test
    void update() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(child));
        service.update(inDto);
        Assert.assertNotNull(child);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.update(inDto));
    }


    @DisplayName("convertToEntity ok")
    @Test
    void convertToEntity() {
        service.convertToEntity(inDto, pacient);
        Assert.assertNotNull(child);
    }

}