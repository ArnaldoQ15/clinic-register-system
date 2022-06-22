package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPhoneRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonPhoneInDto;
import com.br.clinicregistersystem.dto.PersonPhoneOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonPhone;
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

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonPhoneServiceTest {

    @InjectMocks
    private PersonPhoneService service;

    @Mock
    private PersonPhoneRepository repository;

    @Mock
    private PersonPhone phone;

    @Mock
    private PersonPhoneInDto inDto;

    @Mock
    private PersonPhoneOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Person person;

    @Mock
    private PersonRepository personRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAllById ok")
    @Test
    void findAllById() {
        List<PersonPhone> phones = new ArrayList<>();
        phones.add(phone);

        when(repository.findByPersonId(anyLong())).thenReturn(phones);
        service.findAllById(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("persist Ok")
    @Test
    void persistOk(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(modelMapper.map(inDto, PersonPhone.class)).thenReturn(phone);
        service.persist(anyLong(), inDto);
        Assert.assertNotNull(phone);
    }


    @DisplayName("persist NotFoundException")
    @Test
    void persistNotFoundException(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.persist(anyLong(), inDto));
    }


    @DisplayName("update Ok")
    @Test
    void updateOk() {
        when(repository.findByPersonIdAndPhoneId(anyLong(), anyLong())).thenReturn(Optional.of(phone));
        service.update(1L, inDto);
        Assert.assertNotNull(phone);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findByPersonIdAndPhoneId(anyLong(), anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.update(1L, inDto));
    }


    @DisplayName("convertToEntity ok")
    @Test
    void convertToEntity() {
        List<PersonPhoneInDto> phoneInDtos = new ArrayList<>();
        phoneInDtos.add(inDto);
        List<PersonPhone> phones = new ArrayList<>();
        when(modelMapper.map(inDto, PersonPhone.class)).thenReturn(phone);
        service.convertListToEntity(phoneInDtos, person);
        Assert.assertNotNull(phones);
    }


    @DisplayName("delete ok")
    @Test
    void deleteOk() {
        when(repository.findByPersonIdAndPhoneId(anyLong(), anyLong())).thenReturn(Optional.of(phone));
        service.delete(anyLong(), anyLong());
        Assert.assertNotNull(phone);
    }


    @DisplayName("delete NotFoundException")
    @Test
    void deleteNotFoundException() {
        when(repository.findByPersonIdAndPhoneId(anyLong(), anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.delete(anyLong(), anyLong()));
    }

}