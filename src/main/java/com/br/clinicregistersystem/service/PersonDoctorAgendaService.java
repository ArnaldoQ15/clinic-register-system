package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorAgendaRepository;
import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.PersonDoctorAgendaDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.DayWeek;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonDoctorAgenda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.br.clinicregistersystem.model.DayWeek.*;

@Service
public class PersonDoctorAgendaService {

    @Autowired
    private PersonDoctorAgendaRepository repository;

    @Autowired
    private PersonDoctorRepository personDoctorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<PersonDoctorAgenda> createAgenda(PersonDoctor doctor){
        List<PersonDoctorAgenda> fullAgenda = new ArrayList<>();

        PersonDoctorAgenda agenda = new PersonDoctorAgenda();
        agenda.setPersonDoctor(doctor);
        agenda.setDataStatus(true);
        agenda.setMedicalEspeciality(doctor.getMedicalEspeciality());

        PersonDoctorAgenda agenda2 = new PersonDoctorAgenda();
        agenda2.setPersonDoctor(doctor);
        agenda2.setDataStatus(true);
        agenda2.setMedicalEspeciality(doctor.getMedicalEspeciality());

        switch (doctor.getMedicalEspeciality()) {
            case ANGIOLOGY -> {
                agenda.setDayWeek(MONDAY);
                morning(agenda);
                afternoon(agenda);
                fullAgenda.add(agenda);
                repository.save(agenda);


                agenda2.setDayWeek(SATURDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
                repository.save(agenda2);
            }

            case CARDIOLOGY -> {
                agenda.setDayWeek(TUESDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);
                repository.save(agenda);

                agenda2.setDayWeek(WEDNESDAY);
                afternoon(agenda2);
                fullAgenda.add(agenda2);
                repository.save(agenda2);
            }
        }

        return fullAgenda;
    }


    public ResponseEntity<ConsultInDto> validateConsultAvailable(ConsultInDto dto, Long personId) {
        List<PersonDoctorAgenda> doctorAgendaList = repository.findByPersonId(personId);
        if (doctorAgendaList.isEmpty())
            throw new NotFoundException("Agenda not found.");

        PersonDoctorAgenda entityNew = new PersonDoctorAgenda();
        doctorAgendaList.forEach(agenda -> {
            if (agenda.getDayWeek().equals(dto.getDayRequest()))
                BeanUtils.copyProperties(agenda, entityNew);
        });

        validateConsultHour(dto, entityNew);
        return ResponseEntity.ok().build();
    }


    private void morning(PersonDoctorAgenda entity) {
        entity.setMorning0800(true);
        entity.setMorning0830(true);
        entity.setMorning0900(true);
        entity.setMorning0930(true);
        entity.setMorning1000(true);
        entity.setMorning1030(true);
        entity.setMorning1100(true);
        entity.setMorning1130(true);
    }


    private void afternoon(PersonDoctorAgenda entity) {
        entity.setAfternoon1400(true);
        entity.setAfternoon1430(true);
        entity.setAfternoon1500(true);
        entity.setAfternoon1530(true);
        entity.setAfternoon1600(true);
        entity.setAfternoon1630(true);
        entity.setAfternoon1700(true);
        entity.setAfternoon1730(true);
    }


    private void night(PersonDoctorAgenda entity) {
        entity.setNight1800(true);
        entity.setNight1830(true);
        entity.setNight1900(true);
        entity.setNight1930(true);
    }


    private ResponseEntity<ConsultInDto> validateConsultHour(ConsultInDto dto, PersonDoctorAgenda agenda) {
        switch (dto.getHourRequest()) {
            case MORNING0800 -> {
                if (agenda.getMorning0800().equals(true)) {
                    agenda.setMorning0800(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING0830 -> {
                if (agenda.getMorning0830().equals(true)) {
                    agenda.setMorning0830(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING0900 -> {
                if (agenda.getMorning0900().equals(true)) {
                    agenda.setMorning0900(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING0930 -> {
                if (agenda.getMorning0930().equals(true)) {
                    agenda.setMorning0930(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING1000 -> {
                if (agenda.getMorning1000().equals(true)) {
                    agenda.setMorning1000(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING1030 -> {
                if (agenda.getMorning1030().equals(true)) {
                    agenda.setMorning1030(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING1100 -> {
                if (agenda.getMorning1100().equals(true)) {
                    agenda.setMorning1100(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case MORNING1130 -> {
                if (agenda.getMorning1130().equals(true)) {
                    agenda.setMorning1130(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1400 -> {
                if (agenda.getAfternoon1400().equals(true)) {
                    agenda.setAfternoon1400(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1430 -> {
                if (agenda.getAfternoon1430().equals(true)) {
                    agenda.setAfternoon1430(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1500 -> {
                if (agenda.getAfternoon1500().equals(true)) {
                    agenda.setAfternoon1500(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1530 -> {
                if (agenda.getAfternoon1530().equals(true)) {
                    agenda.setAfternoon1530(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1600 -> {
                if (agenda.getAfternoon1600().equals(true)) {
                    agenda.setAfternoon1600(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1630 -> {
                if (agenda.getAfternoon1630().equals(true)) {
                    agenda.setAfternoon1630(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1700 -> {
                if (agenda.getAfternoon1700().equals(true)) {
                    agenda.setAfternoon1700(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case AFTERNOON1730 -> {
                if (agenda.getAfternoon1730().equals(true)) {
                    agenda.setAfternoon1730(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case NIGHT1800 -> {
                if (agenda.getNight1800().equals(true)) {
                    agenda.setNight1800(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case NIGHT1830 -> {
                if (agenda.getNight1830().equals(true)) {
                    agenda.setNight1830(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case NIGHT1900 -> {
                if (agenda.getNight1900().equals(true)) {
                    agenda.setNight1900(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            case NIGHT1930 -> {
                if (agenda.getNight1930().equals(true)) {
                    agenda.setNight1930(false);
                    repository.save(agenda);
                    return ResponseEntity.ok().build();
                }
            }
            default -> throw new BadRequestException("Invalid hour format.");
        }
        throw new BadRequestException("Date/hour requested not available.");
    }


    public List<PersonDoctorAgendaDto> findId(Long personId) {
        List<PersonDoctorAgenda> agendaList = repository.findByPersonId(personId);
        List<PersonDoctorAgendaDto> agendaDtoList = new ArrayList<>();
        agendaList.forEach(agenda -> agendaDtoList.add(modelMapper.map(agenda, PersonDoctorAgendaDto.class)));

        return agendaDtoList;
    }


    public PersonDoctorAgendaDto findAgendaDayById(Long personId, DayWeek dayWeek) {
        PersonDoctorAgendaDto entityNew = new PersonDoctorAgendaDto();
        findId(personId).forEach(personDoctorAgendaDto -> {
            if (personDoctorAgendaDto.getDayWeek().equals(dayWeek))
                BeanUtils.copyProperties(personDoctorAgendaDto, entityNew);
        });
        return entityNew;
    }

}