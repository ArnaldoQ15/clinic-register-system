package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonEmployeeRepository;
import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonEmployee;
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
class PersonEmployeeServiceTest {

    @InjectMocks
    private PersonEmployeeService service;

    @Mock
    private PersonEmployeeRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonAddressService personAddressService;

    @Mock
    private PersonPhoneService personPhoneService;

    @Mock
    private PersonService personService;

    @Mock
    private PersonEmployee employee;

    @Mock
    private PersonEmployeeInDto inDto;

    @Mock
    private PersonEmployeeOutDto outDto;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAll ok")
    @Test
    void findAll() {
        List<PersonEmployee> employees = new ArrayList<>();
        employees.add(employee);

        when(repository.findAll()).thenReturn(employees);
        service.findAll();
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findId ok")
    @Test
    void findIdOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
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
        verify(repository).save(any(PersonEmployee.class));
    }


    @DisplayName("update Ok")
    @Test
    void updateOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        service.update(anyLong(), inDto);
        Assert.assertNotNull(employee);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.update(anyLong(), inDto));
    }


    @DisplayName("update BadRequestException")
    @Test
    void updateBadRequestException() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        when(employee.getInstitucionalEmail()).thenReturn("arnaldo1@arnaldofagundes.com");
        when(inDto.getInstitucionalEmail()).thenReturn("arnaldo2@arnaldofagundes.com");
        when(repository.existsByInstitucionalEmail(inDto.getInstitucionalEmail())).thenReturn(true);
        Assert.assertThrows(BadRequestException.class, () -> service.update(anyLong(), inDto));
    }


    @DisplayName("delete ok")
    @Test
    void deleteOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
        service.delete(anyLong());
        Assert.assertNotNull(employee);
    }


    @DisplayName("delete NotFoundException")
    @Test
    void deleteNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.delete(anyLong()));
    }

}