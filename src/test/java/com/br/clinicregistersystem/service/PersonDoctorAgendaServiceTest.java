package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorAgendaRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.PersonDoctorAgendaDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonDoctorAgenda;
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

import static com.br.clinicregistersystem.util.enums.DayHour.*;
import static com.br.clinicregistersystem.util.enums.DayWeek.MONDAY;
import static com.br.clinicregistersystem.util.enums.MedicalEspeciality.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@Data
@RunWith(MockitoJUnitRunner.class)
class PersonDoctorAgendaServiceTest {

    @InjectMocks
    private PersonDoctorAgendaService service;

    @Mock
    private PersonDoctorAgendaRepository repository;

    @Mock
    private PersonDoctorAgenda agenda;

    @Mock
    private PersonDoctorAgenda agenda2;

    @Mock
    private PersonDoctor doctor;

    @Mock
    private PersonDoctorAgendaDto dto;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ConsultInDto inDto;

    @Mock
    private Consult consult;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("createAgenda ok")
    @Test
    void createAgendaOk() {
        when(doctor.getMedicalEspeciality()).thenReturn(ANGIOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(CARDIOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(DERMATOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(GASTROENTEROLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(GYNECOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(MASTOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(MEDICAL_CLINIC);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(NUTRITION);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(PEDIATRICS);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(PSYCHOLOGY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(SPEECH_THERAPY);
        service.createAgenda(doctor);
        when(doctor.getMedicalEspeciality()).thenReturn(UROLOGY);
        service.createAgenda(doctor);
        Assert.assertNotNull(agenda);
    }


    @DisplayName("validateConsultAvailable ok")
    @Test
    void validateConsultAvailableOk() {
        List<PersonDoctorAgenda> agendaList = new ArrayList<>();
        agendaList.add(agenda);
        when(agenda.getDayWeek()).thenReturn(MONDAY);
        when(repository.findByPersonId(anyLong())).thenReturn(agendaList);
        when(inDto.getHourRequest()).thenReturn(MORNING0800);
        when(inDto.getDayRequest()).thenReturn(MONDAY);
        when(agenda.getMorning0800().equals(true)).thenReturn(true);
        when(service.validateConsultHour(inDto, agenda)).thenReturn(ResponseEntity.ok().build());
        service.validateConsultAvailable(inDto, anyLong());
    }


    @DisplayName("validateConsultAvailable NotFoundException")
    @Test
    void validateConsultAvailableNotFoundException() {
        List<PersonDoctorAgenda> agendaList = new ArrayList<>();
        when(repository.findByPersonId(anyLong())).thenReturn(agendaList);
        Assert.assertThrows(NotFoundException.class, () -> service.validateConsultAvailable(inDto, anyLong()));
    }


    @DisplayName("validateConsultHour ok")
    @Test
    void validateConsultHourOk() {
        when(inDto.getHourRequest()).thenReturn(MORNING0830);
        when(agenda.getMorning0830().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING0900);
        when(agenda.getMorning0900().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING0930);
        when(agenda.getMorning0930().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING1000);
        when(agenda.getMorning1000().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING1030);
        when(agenda.getMorning1030().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING1100);
        when(agenda.getMorning1100().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(MORNING1130);
        when(agenda.getMorning1130().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1400);
        when(agenda.getAfternoon1400().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1430);
        when(agenda.getAfternoon1430().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1500);
        when(agenda.getAfternoon1500().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1530);
        when(agenda.getAfternoon1530().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1600);
        when(agenda.getAfternoon1600().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1630);
        when(agenda.getAfternoon1630().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1700);
        when(agenda.getAfternoon1700().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(AFTERNOON1730);
        when(agenda.getAfternoon1730().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(NIGHT1800);
        when(agenda.getNight1800().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(NIGHT1830);
        when(agenda.getNight1830().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(NIGHT1900);
        when(agenda.getNight1900().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
        when(inDto.getHourRequest()).thenReturn(NIGHT1930);
        when(agenda.getNight1930().equals(true)).thenReturn(true);
        service.validateConsultHour(inDto, agenda);
    }


    @DisplayName("validateConsultHour BadRequestException")
    @Test
    void validateConsultHourBadRequestException() {
        when(inDto.getHourRequest()).thenReturn(MORNING0800);
        when(agenda.getMorning0830().equals(true)).thenReturn(false);
        Assert.assertThrows(BadRequestException.class, () -> service.validateConsultHour(inDto, agenda));
    }


    @DisplayName("findId ok")
    @Test
    void findId() {
        List<PersonDoctorAgenda> agendaList = new ArrayList<>();
        agendaList.add(agenda);
        when(repository.findByPersonId(anyLong())).thenReturn(agendaList);
        service.findId(anyLong());
        Assert.assertNotNull(agendaList);
    }


    @DisplayName("findAgendaDayById ok")
    @Test
    void findAgendaDayById() {
        List<PersonDoctorAgendaDto> agendaDtos = new ArrayList<>();
        agendaDtos.add(dto);
        List<PersonDoctorAgenda> agendaList = new ArrayList<>();
        agendaList.add(agenda);
        when(repository.findByPersonId(1L)).thenReturn(agendaList);
        when(modelMapper.map(agenda, PersonDoctorAgendaDto.class)).thenReturn(dto);
        when(dto.getDayWeek()).thenReturn(MONDAY);
        when(dto.getMedicalEspeciality()).thenReturn(ANGIOLOGY);
        service.findAgendaDayById(1L, MONDAY);
        Assert.assertNotNull(agendaDtos);
    }


    @DisplayName("enableHourAgenda ok")
    @Test
    void enableHourAgendaOk() {
        when(repository.findByMedicalEspecialityAndDayWeek(consult.getMedicalEspeciality(), consult.getDayRequest()))
                .thenReturn(Optional.of(agenda));
        when(consult.getHourRequest()).thenReturn(MORNING0800);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING0830);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING0900);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING0930);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING1000);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING1030);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING1100);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(MORNING1130);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1400);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1430);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1500);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1530);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1600);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1630);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1700);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(AFTERNOON1730);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(NIGHT1800);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(NIGHT1830);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(NIGHT1900);
        service.enableHourAgenda(consult);
        when(consult.getHourRequest()).thenReturn(NIGHT1930);
        service.enableHourAgenda(consult);
        Assert.assertNotNull(consult);
    }


    @DisplayName("enableHourAgenda NotFoundException")
    @Test
    void enableHourAgendaNotFoundException() {
        when(repository.findByMedicalEspecialityAndDayWeek(consult.getMedicalEspeciality(), consult.getDayRequest()))
                .thenReturn(Optional.empty());
        Assert.assertThrows(NotFoundException.class, () -> service.enableHourAgenda(consult));
    }

}