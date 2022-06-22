package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonPacientHealthInsuranceRepository;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceInDto;
import com.br.clinicregistersystem.dto.PersonPacientHealthInsuranceOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonPacient;
import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import lombok.Data;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonPacientHealthInsuranceServiceTest {

    @InjectMocks
    private PersonPacientHealthInsuranceService service;

    @Mock
    private PersonPacientHealthInsuranceRepository repository;

    @Mock
    private PersonPacientHealthInsurance healthInsurance;

    @Mock
    private PersonPacientHealthInsuranceInDto inDto;

    @Mock
    private PersonPacientHealthInsuranceOutDto outDto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonPacient pacient;


    @BeforeEach
    public void init() {
        initMocks(this);
    }


    @DisplayName("findId ok")
    @Test
    void findIdOk() {
        when(repository.findByPersonId(anyLong())).thenReturn(Optional.of(healthInsurance));
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
        when(repository.findByPersonId(anyLong())).thenReturn(Optional.of(healthInsurance));
        service.update(anyLong(), inDto);
        Assert.assertNotNull(pacient);
    }


    @DisplayName("update NotFoundException")
    @Test
    void updateNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.update(anyLong(), inDto));
    }


    @DisplayName("convertToEntity ok")
    @Test
    void convertToEntity() {
        when(modelMapper.map(inDto, PersonPacientHealthInsurance.class)).thenReturn(healthInsurance);
        service.convertToEntity(inDto);
    }

}