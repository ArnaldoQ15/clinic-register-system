package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorAgendaRepository;
import com.br.clinicregistersystem.dto.ConsultInDto;
import com.br.clinicregistersystem.dto.PersonDoctorAgendaDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonDoctorAgenda;
import com.br.clinicregistersystem.util.enums.DayWeek;
import com.br.clinicregistersystem.util.statics.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.br.clinicregistersystem.util.enums.DayWeek.*;

@Service
public class PersonDoctorAgendaService {

    @Autowired
    private PersonDoctorAgendaRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    /**Save a doctor agenda on database.*/
    public List<PersonDoctorAgenda> createAgenda(PersonDoctor doctor){
        List<PersonDoctorAgenda> fullAgenda = new ArrayList<>();

        PersonDoctorAgenda agenda = new PersonDoctorAgenda();
        agenda.setPersonDoctor(doctor);
        agenda.setDoctorName(doctor.getDoctorName());
        agenda.setDataStatus(true);
        agenda.setMedicalEspeciality(doctor.getMedicalEspeciality());

        PersonDoctorAgenda agenda2 = new PersonDoctorAgenda();
        agenda2.setPersonDoctor(doctor);
        agenda2.setDoctorName(doctor.getDoctorName());
        agenda2.setDataStatus(true);
        agenda2.setMedicalEspeciality(doctor.getMedicalEspeciality());

        switch (doctor.getMedicalEspeciality()) {
            case ANGIOLOGY -> {
                agenda.setDayWeek(MONDAY);
                morning(agenda);
                afternoon(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(SATURDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case CARDIOLOGY -> {
                agenda.setDayWeek(TUESDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(WEDNESDAY);
                afternoon(agenda2);
                fullAgenda.add(agenda2);
            }

            case MEDICAL_CLINIC -> {
                agenda.setDayWeek(THURSDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(MONDAY);
                afternoon(agenda2);
                night(agenda2);
                fullAgenda.add(agenda2);
            }

            case DERMATOLOGY -> {
                agenda.setDayWeek(FRIDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(THURSDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case SPEECH_THERAPY -> {
                agenda.setDayWeek(WEDNESDAY);
                morning(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(THURSDAY);
                afternoon(agenda2);
                night(agenda2);
                fullAgenda.add(agenda2);
            }

            case GASTROENTEROLOGY -> {
                agenda.setDayWeek(MONDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(FRIDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case GYNECOLOGY -> {
                agenda.setDayWeek(TUESDAY);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(SATURDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case MASTOLOGY -> {
                agenda.setDayWeek(MONDAY);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(TUESDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case NUTRITION -> {
                agenda.setDayWeek(THURSDAY);
                morning(agenda);
                afternoon(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(SATURDAY);
                morning(agenda2);
                fullAgenda.add(agenda2);
            }

            case PEDIATRICS -> {
                agenda.setDayWeek(MONDAY);
                morning(agenda);
                afternoon(agenda);
                night(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(WEDNESDAY);
                afternoon(agenda2);
                night(agenda2);
                fullAgenda.add(agenda2);
            }

            case PSYCHOLOGY -> {
                agenda.setDayWeek(TUESDAY);
                morning(agenda);
                afternoon(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(FRIDAY);
                afternoon(agenda2);
                fullAgenda.add(agenda2);
            }

            case UROLOGY -> {
                agenda.setDayWeek(THURSDAY);
                afternoon(agenda);
                fullAgenda.add(agenda);

                agenda2.setDayWeek(FRIDAY);
                morning(agenda2);
                afternoon(agenda2);
                night(agenda2);
                fullAgenda.add(agenda2);
            }
        }

        return fullAgenda;
    }


    /**Validate disponibility of a consult.*/
    public ResponseEntity<ConsultInDto> validateConsultAvailable(ConsultInDto dto, Long personId) {
        List<PersonDoctorAgenda> doctorAgendaList = repository.findByPersonId(personId);
        if (doctorAgendaList.isEmpty())
            throw new NotFoundException(ExceptionMessage.AGENDA_NOT_FOUND);

        PersonDoctorAgenda entityNew = new PersonDoctorAgenda();
        doctorAgendaList.forEach(agenda -> {
            if (agenda.getDayWeek().equals(dto.getDayRequest()))
                BeanUtils.copyProperties(agenda, entityNew);
        });

        validateConsultHour(dto, entityNew);
        entityNew.setLastUpdate(OffsetDateTime.now());
        return ResponseEntity.ok().build();
    }


    /**Method to reduce lines - MORNING.*/
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


    /**Method to reduce lines - AFTERNOON.*/
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


    /**Method to reduce lines - NIGHT.*/
    private void night(PersonDoctorAgenda entity) {
        entity.setNight1800(true);
        entity.setNight1830(true);
        entity.setNight1900(true);
        entity.setNight1930(true);
    }


    /**Validate hour disponibility of a doctor agenda before save consult.*/
    public ResponseEntity<ConsultInDto> validateConsultHour(ConsultInDto dto, PersonDoctorAgenda agenda) {
        switch (dto.getHourRequest()) {
            case MORNING0800 -> {
                if (agenda.getMorning0800().equals(true)) {
                    agenda.setMorning0800(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING0830 -> {
                if (agenda.getMorning0830().equals(true)) {
                    agenda.setMorning0830(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING0900 -> {
                if (agenda.getMorning0900().equals(true)) {
                    agenda.setMorning0900(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING0930 -> {
                if (agenda.getMorning0930().equals(true)) {
                    agenda.setMorning0930(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING1000 -> {
                if (agenda.getMorning1000().equals(true)) {
                    agenda.setMorning1000(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING1030 -> {
                if (agenda.getMorning1030().equals(true)) {
                    agenda.setMorning1030(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING1100 -> {
                if (agenda.getMorning1100().equals(true)) {
                    agenda.setMorning1100(false);
                    return saveEntity(agenda);
                }
            }
            case MORNING1130 -> {
                if (agenda.getMorning1130().equals(true)) {
                    agenda.setMorning1130(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1400 -> {
                if (agenda.getAfternoon1400().equals(true)) {
                    agenda.setAfternoon1400(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1430 -> {
                if (agenda.getAfternoon1430().equals(true)) {
                    agenda.setAfternoon1430(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1500 -> {
                if (agenda.getAfternoon1500().equals(true)) {
                    agenda.setAfternoon1500(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1530 -> {
                if (agenda.getAfternoon1530().equals(true)) {
                    agenda.setAfternoon1530(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1600 -> {
                if (agenda.getAfternoon1600().equals(true)) {
                    agenda.setAfternoon1600(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1630 -> {
                if (agenda.getAfternoon1630().equals(true)) {
                    agenda.setAfternoon1630(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1700 -> {
                if (agenda.getAfternoon1700().equals(true)) {
                    agenda.setAfternoon1700(false);
                    return saveEntity(agenda);
                }
            }
            case AFTERNOON1730 -> {
                if (agenda.getAfternoon1730().equals(true)) {
                    agenda.setAfternoon1730(false);
                    return saveEntity(agenda);
                }
            }
            case NIGHT1800 -> {
                if (agenda.getNight1800().equals(true)) {
                    agenda.setNight1800(false);
                    return saveEntity(agenda);
                }
            }
            case NIGHT1830 -> {
                if (agenda.getNight1830().equals(true)) {
                    agenda.setNight1830(false);
                    return saveEntity(agenda);
                }
            }
            case NIGHT1900 -> {
                if (agenda.getNight1900().equals(true)) {
                    agenda.setNight1900(false);
                    return saveEntity(agenda);
                }
            }
            case NIGHT1930 -> {
                if (agenda.getNight1930().equals(true)) {
                    agenda.setNight1930(false);
                    return saveEntity(agenda);
                }
            }
        }
        throw new BadRequestException(ExceptionMessage.AGENDA_BADREQUEST);
    }


    private ResponseEntity<ConsultInDto> saveEntity(PersonDoctorAgenda agenda) {
        repository.save(agenda);
        return ResponseEntity.ok().build();
    }


    /**Find an agenda by Person (Doctor) ID.*/
    public List<PersonDoctorAgendaDto> findId(Long personId) {
        List<PersonDoctorAgenda> agendaList = repository.findByPersonId(personId);
        List<PersonDoctorAgendaDto> agendaDtoList = new ArrayList<>();
        agendaList.forEach(agenda -> agendaDtoList.add(modelMapper.map(agenda, PersonDoctorAgendaDto.class)));
        return agendaDtoList;
    }


    /**Find all doctor agenda (week) by Person ID.*/
    public PersonDoctorAgendaDto findAgendaDayById(Long personId, DayWeek dayWeek) {
        PersonDoctorAgendaDto entityNew = new PersonDoctorAgendaDto();
        findId(personId).forEach(personDoctorAgendaDto -> {
            if (personDoctorAgendaDto.getDayWeek().equals(dayWeek))
                BeanUtils.copyProperties(personDoctorAgendaDto, entityNew);});
        return entityNew;
    }


    /**Activate available medical hours of canceled consult.*/
    public void enableHourAgenda(Consult consult) {
        Optional<PersonDoctorAgenda> agendaOptional = repository.findByMedicalEspecialityAndDayWeek(consult.getMedicalEspeciality(),
                consult.getDayRequest());
        if (agendaOptional.isEmpty())
            throw new NotFoundException(ExceptionMessage.AGENDA_NOT_FOUND);
        PersonDoctorAgenda agenda = agendaOptional.get();
        switch (consult.getHourRequest()) {
            case MORNING0800 -> agenda.setMorning0800(true);
            case MORNING0830 -> agenda.setMorning0830(true);
            case MORNING0900 -> agenda.setMorning0900(true);
            case MORNING0930 -> agenda.setMorning0930(true);
            case MORNING1000 -> agenda.setMorning1000(true);
            case MORNING1030 -> agenda.setMorning1030(true);
            case MORNING1100 -> agenda.setMorning1100(true);
            case MORNING1130 -> agenda.setMorning1130(true);
            case AFTERNOON1400 -> agenda.setAfternoon1400(true);
            case AFTERNOON1430 -> agenda.setAfternoon1430(true);
            case AFTERNOON1500 -> agenda.setAfternoon1500(true);
            case AFTERNOON1530 -> agenda.setAfternoon1530(true);
            case AFTERNOON1600 -> agenda.setAfternoon1600(true);
            case AFTERNOON1630 -> agenda.setAfternoon1630(true);
            case AFTERNOON1700 -> agenda.setAfternoon1700(true);
            case AFTERNOON1730 -> agenda.setAfternoon1730(true);
            case NIGHT1800 -> agenda.setNight1800(true);
            case NIGHT1830 -> agenda.setNight1830(true);
            case NIGHT1900 -> agenda.setNight1900(true);
            case NIGHT1930 -> agenda.setNight1930(true);
        }
        agenda.setLastUpdate(OffsetDateTime.now());
        repository.save(agenda);
    }

}