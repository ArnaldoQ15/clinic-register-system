package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonDoctor;
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
class PersonDoctorServiceTest {

    @InjectMocks
    private PersonDoctorService service;

    @Mock
    private PersonDoctorRepository repository;

    @Mock
    private PersonDoctor doctor;

    @Mock
    private PersonDoctorInDto inDto;

    @Mock
    private PersonDoctorOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonService personService;

    @Mock
    private PersonAddressService personAddressService;

    @Mock
    private PersonPhoneService personPhoneService;

    @Mock
    private PersonDoctorAgendaService personDoctorAgendaService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("persist ok")
    @Test
    void persistOk() {
        when(inDto.getPersonDocumentCpf()).thenReturn("254.674.480-13");
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        when(inDto.getPersonBirthday()).thenReturn(LocalDate.now());

        service.persist(inDto);
        verify(repository).saveAndFlush(any(PersonDoctor.class));
    }

    @DisplayName("findAll ok")
    @Test
    void findAllOk() {
        List<PersonDoctor> doctors = new ArrayList<>();
        doctors.add(doctor);

        when(repository.findAll()).thenReturn(doctors);
        service.findAll();
        Assert.assertNotNull(outDto);
    }

    @DisplayName("findId ok")
    @Test
    void findIdOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        service.findId(anyLong());
        Assert.assertNotNull(outDto);
    }

    @DisplayName("findId NotFoundException")
    @Test
    void findIdNotFoundException() {
        Assert.assertThrows(NotFoundException.class, () -> service.findId(anyLong()));
    }

    @DisplayName("update Ok")
    @Test
    void updateOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        service.update(anyLong(), inDto);
        Assert.assertNotNull(doctor);
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        service.delete(anyLong());
        Assert.assertNotNull(doctor);
    }

    @DisplayName("delete NotFoundException")
    @Test
    void deleteNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.delete(anyLong()));
    }

    @DisplayName("renewValidity ok")
    @Test
    void renewValidityOk() {
        PersonDoctor doctorTest = new PersonDoctor();
        LocalDate validity = LocalDate.of((LocalDate.now().getYear()), (LocalDate.now().getMonthValue() + 1),
                LocalDate.now().getDayOfMonth());
        doctorTest.setProfessionalRegisterValidity(validity);
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctorTest));
        service.renewValidity(anyLong());
        Assert.assertNotNull(doctor);
    }

    @DisplayName("renewValidity NotFoundException")
    @Test
    void renewValidityNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.renewValidity(anyLong()));
    }

    @DisplayName("convertToInfo ok")
    @Test
    void convertToInfoOk() {
        List<PersonDoctor> doctors = new ArrayList<>();
        when(repository.findAll()).thenReturn(doctors);
        service.convertToInfo();
        Assert.assertNotNull(doctors);
    }

}