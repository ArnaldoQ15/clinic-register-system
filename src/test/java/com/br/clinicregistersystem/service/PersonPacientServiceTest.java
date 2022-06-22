package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.PersonPacientInDto;
import com.br.clinicregistersystem.dto.PersonPacientOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonPacientServiceTest {

    @InjectMocks
    private PersonPacientService service;

    @Mock
    private PersonPacientRepository repository;

    @Mock
    private PersonPacient pacient;

    @Mock
    private PersonPacientInDto inDto;

    @Mock
    private PersonPacientOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonService personService;

    @Mock
    private PersonAddressService personAddressService;

    @Mock
    private PersonPhoneService personPhoneService;

    @Mock
    private PersonProntuaryService personProntuaryService;

    @Mock
    private PersonPacientHealthInsuranceService personPacientHealthInsuranceService;

    @Mock
    private PersonPacientChildService personPacientChildService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAll ok")
    @Test
    void findAll() {
        List<PersonPacient> pacients = new ArrayList<>();
        pacients.add(pacient);

        when(repository.findAll()).thenReturn(pacients);
        service.findAll();
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findId ok")
    @Test
    void findIdOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pacient));
        service.findId(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findId NotFoundException")
    @Test
    void findIdNotFoundException() {
        Assert.assertThrows(NotFoundException.class, () -> service.findId(anyLong()));
    }


    @DisplayName("persist ok")
    @Test
    void persist() {
        when(inDto.getPersonDocumentCpf()).thenReturn("254.674.480-13");
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        when(inDto.getPersonBirthday()).thenReturn(LocalDate.now());

        service.persist(inDto);
        verify(repository).saveAndFlush(any(PersonPacient.class));
    }


    @DisplayName("update Ok")
    @Test
    void updateOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pacient));
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        service.update(anyLong(), inDto);
        Assert.assertNotNull(pacient);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.update(anyLong(), inDto));
    }


    @DisplayName("delete ok")
    @Test
    void deleteOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pacient));
        service.delete(anyLong());
        Assert.assertNotNull(pacient);
    }


    @DisplayName("delete NotFoundException")
    @Test
    void deleteNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.delete(anyLong()));
    }

}