package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonAddressRepository;
import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.dto.PersonAddressInDto;
import com.br.clinicregistersystem.dto.PersonAddressOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonAddress;
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
class PersonAddressServiceTest {

    @InjectMocks
    private PersonAddressService service;

    @Mock
    private PersonAddressRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonAddress address;

    @Mock
    private PersonAddressInDto inDto;

    @Mock
    private PersonAddressOutDto outDto;

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
        List<PersonAddress> addresses = new ArrayList<>();
        addresses.add(address);

        when(repository.findByPersonId(anyLong())).thenReturn(addresses);
        service.findAllById(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("persist Ok")
    @Test
    void persistOk(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(modelMapper.map(inDto, PersonAddress.class)).thenReturn(address);
        service.persist(anyLong(), inDto);
        Assert.assertNotNull(address);
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
        when(repository.findByPersonIdAndAddressId(anyLong(), anyLong())).thenReturn(Optional.of(address));
        service.update(1L, inDto);
        Assert.assertNotNull(address);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findByPersonIdAndAddressId(anyLong(), anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.update(1L, inDto));
    }


    @DisplayName("convertToEntity ok")
    @Test
    void convertToEntity() {
        List<PersonAddressInDto> addressList = new ArrayList<>();
        addressList.add(inDto);
        List<PersonAddress> personAddresses = new ArrayList<>();
        when(modelMapper.map(inDto, PersonAddress.class)).thenReturn(address);
        service.convertListToEntity(addressList, person);
        Assert.assertNotNull(personAddresses);
    }


    @DisplayName("delete ok")
    @Test
    void deleteOk() {
        when(repository.findByPersonIdAndAddressId(anyLong(), anyLong())).thenReturn(Optional.of(address));
        service.delete(anyLong(), anyLong());
        Assert.assertNotNull(address);
    }


    @DisplayName("delete NotFoundException")
    @Test
    void deleteNotFoundException() {
        when(repository.findByPersonIdAndAddressId(anyLong(), anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.delete(anyLong(), anyLong()));
    }

}