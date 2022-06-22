package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.model.Person;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private Person person;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("validateCpfExists ok")
    @Test
    void validateCpfExistsOk() {
        when(repository.existsByPersonDocumentCpf(person.getPersonDocumentCpf())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> service.validateCpfExists(person.getPersonDocumentCpf()));
    }


    @DisplayName("validateEmailExists ok")
    @Test
    void validateEmailExists() {
        when(repository.existsByPersonEmail(person.getPersonEmail())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> service.validateEmailExists(person.getPersonEmail()));
    }


    @DisplayName("insertAge ok")
    @Test
    void insertAge() {
        service.insertAge(LocalDate.now());
        assertEquals(LocalDate.now(), LocalDate.now());
    }


    @DisplayName("makeBirthday ok")
    @Test
    void makeBirthday() {
        service.makeBirthday(LocalDate.now(), 22);
        assertEquals(22, 22);
    }


    @DisplayName("makeBirthday BadRequestException")
    @Test
    void makeBirthdayBadRequestException() {
        assertThrows(BadRequestException.class, () -> service.makeBirthday(LocalDate.of(LocalDate.now().getYear(),
                LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth()), 22));
    }

}