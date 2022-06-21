package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.PersonAddress;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPhone;
import lombok.Getter;
import lombok.Setter;
import net.minidev.asm.ConvertDate;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.br.clinicregistersystem.model.FederativeUnits.BA;
import static com.br.clinicregistersystem.model.PersonPhoneType.CELLPHONE;
import static com.br.clinicregistersystem.model.PersonSex.MALE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Getter
@Setter
@RunWith(MockitoJUnitRunner.class)
class PersonDoctorServiceTest {

    @InjectMocks
    private PersonDoctorService service;

    @Mock
    private PersonService personService;

    @Mock
    private PersonAddressService personAddressService;

    @Mock
    private PersonPhoneService personPhoneService;

    @Mock
    private PersonDoctorAgendaService personDoctorAgendaService;

    @Mock
    private PersonDoctorRepository repository;

    @Mock
    private PersonDoctor doctor;

    @Mock
    private PersonDoctorInDto inDto;

    @Mock
    private PersonDoctorOutDto outDto;

    @Mock
    private List<PersonDoctor> doctors;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("persist ok")
    @Test
    public void persistOk() {
        when(inDto.getPersonDocumentCpf()).thenReturn("254.674.480-13");
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        when(inDto.getPersonBirthday()).thenReturn(LocalDate.now());

        service.persist(inDto);
        verify(repository).saveAndFlush(any(PersonDoctor.class));
    }

    @DisplayName("findAll ok")
    @Test
    public void findAllOk() {
        service.findAll();
        Assert.assertNotNull(outDto);
    }

    @DisplayName("findId ok")
    @Test
    public void findIdOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        service.findId(anyLong());
        Assert.assertNotNull(outDto);
    }

    @DisplayName("findId NotFoundException")
    @Test
    public void findIdNotFoundException() {
        Assert.assertThrows(NotFoundException.class, () -> service.findId(anyLong()));
    }

    @DisplayName("update Ok")
    @Test
    public void updateOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        when(inDto.getPersonEmail()).thenReturn("arnaldo.s.fagundes@gmail.com");
        service.update(anyLong(), inDto);
        Assert.assertNotNull(doctor);
    }

    @DisplayName("update NotFoundException")
    @Test
    public void updateNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.update(anyLong(), inDto));
    }

    @DisplayName("delete ok")
    @Test
    public void deleteOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        service.delete(anyLong());
        Assert.assertNotNull(doctor);
    }

    @DisplayName("delete NotFoundException")
    @Test
    public void deleteNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.delete(anyLong()));
    }

    @DisplayName("renewValidity ok")
    @Test
    public void renewValidityOk() {
        doctor.setProfessionalRegisterValidity(LocalDate.now());
        when(repository.findById(anyLong())).thenReturn(Optional.of(doctor));
        service.renewValidity(anyLong());
        Assert.assertNotNull(doctor);
    }

    @DisplayName("renewValidity NotFoundException")
    @Test
    public void renewValidityNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.renewValidity(anyLong()));
    }







    private PersonDoctor doctorMock() {
        PersonDoctor personDoctor = new PersonDoctor();
        personDoctor.setPersonId(1L);
        personDoctor.setPersonDocumentCpf("254.674.480-13");
        personDoctor.setPersonEmail("arnaldo.s.fagundes@gmail.com");
        personDoctor.setPersonSex(MALE);
        personDoctor.getPersonAddresses().add(addressMock());
        personDoctor.getPersonPhones().add(phoneMock());
        return personDoctor;
    }

    private PersonAddress addressMock() {
        PersonAddress address = new PersonAddress();
        address.setNumber("54");
        address.setCity("Salvador");
        address.setState(BA);
        address.setStreet("Rua das gaivotas");
        address.setDistrict("Andorinhas");
        address.setAddressId(1L);
        return address;
    }

    private PersonPhone phoneMock() {
        PersonPhone phone = new PersonPhone();
        phone.setNumber(65158148864L);
        phone.setPersonPhoneName("Arnaldo");
        phone.setType(CELLPHONE);
        phone.setPhoneId(1L);
        return phone;
    }

}
