package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.ConsultOutDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.util.enums.ConsultStatus;
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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class ConsultServiceTest {

    @InjectMocks
    private ConsultService service;

    @Mock
    private ConsultRepository repository;

    @Mock
    private Consult consult;

    @Mock
    private PersonPacient pacient;

    @Mock
    private PersonDoctor doctor;

    @Mock
    private ConsultInDto inDto;

    @Mock
    private ConsultOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonPacientRepository pacientRepository;

    @Mock
    private PersonDoctorRepository doctorRepository;

    @Mock
    private PersonDoctorAgendaService personDoctorAgendaService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("findAll ok")
    @Test
    void findAllOk() {
        List<Consult> consults = new ArrayList<>();
        consults.add(consult);
        when(repository.findByPersonId(anyLong())).thenReturn(consults);
        service.findAll();
        Assert.assertNotNull(outDto);
    }


    @DisplayName("findId ok")
    @Test
    void findIdOk() {
        List<Consult> consultList = new ArrayList<>();
        when(repository.findByPersonId(anyLong())).thenReturn(consultList);
        service.findId(anyLong());
        Assert.assertNotNull(outDto);
    }


    @DisplayName("persist Ok")
    @Test
    void persistOk() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.of(pacient));
        when(doctorRepository.findByMedicalEspeciality(consult.getMedicalEspeciality())).thenReturn(Optional.of(doctor));
        when(personDoctorAgendaService.validateConsultAvailable(inDto, doctor.getPersonId())).thenReturn(ResponseEntity.ok().build());
        service.persist(inDto, anyLong());
        Assert.assertNotNull(consult);
    }


    @DisplayName("persist NotFoundException1")
    @Test
    void persistNotFoundException1() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.persist(inDto, anyLong()));
    }


    @DisplayName("persist NotFoundException2")
    @Test
    void persistNotFoundException2() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.of(pacient));
        when(doctorRepository.findByMedicalEspeciality(consult.getMedicalEspeciality())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.persist(inDto, anyLong()));
    }


    @DisplayName("persist BadRequestException")
    @Test
    void persistBadRequestException() {
        when(pacientRepository.findById(anyLong())).thenReturn(Optional.of(pacient));
        when(doctorRepository.findByMedicalEspeciality(consult.getMedicalEspeciality())).thenReturn(Optional.of(doctor));
        when(personDoctorAgendaService.validateConsultAvailable(inDto, doctor.getPersonId())).thenReturn(ResponseEntity.notFound().build());
        assertThrows(BadRequestException.class, () -> service.persist(inDto, anyLong()));
    }


    @DisplayName("deleteByPacient ok")
    @Test
    void deleteByPacientOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(consult));
        when(consult.getStatus()).thenReturn(ConsultStatus.PENDING_AUTHORIZATION);
        service.deleteByPacient(anyLong());
        Assert.assertNotNull(consult);
    }


    @DisplayName("deleteByPacient NotFoundException")
    @Test
    void deleteByPacientNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.deleteByPacient(anyLong()));
    }


    @DisplayName("deleteByPacient BadRequestException")
    @Test
    void deleteByPacientBadRequestException() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(consult));
        when(consult.getStatus()).thenReturn(ConsultStatus.CANCELED_BY_CLINIC);
        assertThrows(BadRequestException.class, () -> service.deleteByPacient(anyLong()));
    }


    @DisplayName("deleteByClinic ok")
    @Test
    void deleteByClinicOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(consult));
        when(consult.getStatus()).thenReturn(ConsultStatus.PENDING_AUTHORIZATION);
        service.deleteByClinic(anyLong());
        Assert.assertNotNull(consult);
    }


    @DisplayName("deleteByClinic NotFoundException")
    @Test
    void deleteByClinicNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.deleteByClinic(anyLong()));
    }


    @DisplayName("deleteByClinic BadRequestException")
    @Test
    void deleteByClinicBadRequestException() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(consult));
        when(consult.getStatus()).thenReturn(ConsultStatus.CANCELED_BY_CLINIC);
        assertThrows(BadRequestException.class, () -> service.deleteByClinic(anyLong()));
    }



}