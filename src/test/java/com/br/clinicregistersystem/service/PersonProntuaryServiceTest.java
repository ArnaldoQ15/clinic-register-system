package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.domain.repository.PersonProntuaryRepository;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryInDto;
import com.br.clinicregistersystem.dto.PersonPacientProntuaryOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
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
class PersonProntuaryServiceTest {

    @InjectMocks
    private PersonProntuaryService service;

    @Mock
    private PersonProntuaryRepository repository;

    @Mock
    private PersonPacientProntuary prontuary;

    @Mock
    private PersonPacientProntuaryInDto inDto;

    @Mock
    private PersonPacientProntuaryOutDto outDto;

    @Mock
    private PersonPacientRepository pacientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonPacient pacient;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAllById ok")
    @Test
    void findAllById() {
        List<PersonPacientProntuary> prontuaries = new ArrayList<>();
        PersonPacient pacient = new PersonPacient();
        prontuaries.add(prontuary);
        when(prontuary.getPacient()).thenReturn(pacient);
        when(repository.findByPersonId(anyLong())).thenReturn(prontuaries);
        when(modelMapper.map(prontuary, PersonPacientProntuaryOutDto.class)).thenReturn(outDto);
        service.findAllById(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findAllById NotFoundException")
    @Test
    void findAllByIdNotFoundException() {
        Assert.assertThrows(NotFoundException.class, () -> service.findAllById(anyLong()));
    }


    @DisplayName("persist Ok")
    @Test
    void persistOk() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.of(pacient));
        when(modelMapper.map(inDto, PersonPacientProntuary.class)).thenReturn(prontuary);
        service.persist(anyLong(), inDto);
    }


    @DisplayName("persist NotFoundException")
    @Test
    void persistNotFoundException() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.persist(anyLong(), inDto));
    }


    @DisplayName("update Ok")
    @Test
    void update() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(prontuary));
        service.update(inDto);
        Assert.assertNotNull(prontuary);
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
        when(modelMapper.map(inDto, PersonPacientProntuary.class)).thenReturn(prontuary);
        service.convertToEntity(inDto, pacient);
        Assert.assertNotNull(prontuary);
    }

}