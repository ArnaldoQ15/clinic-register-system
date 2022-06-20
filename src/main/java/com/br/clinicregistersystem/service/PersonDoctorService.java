package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.PersonDoctorInDto;
import com.br.clinicregistersystem.dto.PersonDoctorInformationDto;
import com.br.clinicregistersystem.dto.PersonDoctorOutDto;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonDoctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDoctorService {

    @Autowired
    private PersonDoctorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonAddressService personAddressService;

    @Autowired
    private PersonPhoneService personPhoneService;

    @Autowired
    private PersonDoctorAgendaService personDoctorAgendaService;


    /**Find all doctors on Database.*/
    public List<PersonDoctorOutDto> findAll() {
        List<PersonDoctor> doctorList = repository.findAll();
        List<PersonDoctorOutDto> finalDoctorOutDto = new ArrayList<>();

        doctorList.forEach(personDoctor -> {
            PersonDoctorOutDto doctorOutDto = modelMapper.map(personDoctor, PersonDoctorOutDto.class);
            finalDoctorOutDto.add(doctorOutDto);
        });

        return finalDoctorOutDto;
    }


    /**Search a doctor by Person ID.*/
    public PersonDoctorOutDto findId(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new NotFoundException("Doctor not found.");

        return modelMapper.map(doctor.get(), PersonDoctorOutDto.class);
    }


    /**Save doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctorOutDto> persist(PersonDoctorInDto dto) {
        personService.validateCpfExists(dto.getPersonDocumentCpf());
        personService.validateEmailExists(dto.getPersonEmail());

        PersonDoctor entityNew = new PersonDoctor();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonAge(personService.insertAge(entityNew.getPersonBirthday()));
        entityNew.setPersonAddresses(personAddressService.convertListToEntity(dto.getPersonAddresses(),
                modelMapper.map(entityNew, Person.class)));
        entityNew.setPersonPhones(personPhoneService.convertListToEntity(dto.getPersonPhones(),
                modelMapper.map(entityNew, Person.class)));

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        entityNew.setPersonStatus(true);

        entityNew.setAgenda(personDoctorAgendaService.createAgenda(entityNew));

        repository.saveAndFlush(entityNew);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**Update the doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctor> update(Long personId, PersonDoctorInDto dto) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new NotFoundException("Doctor not found.");

        PersonDoctor entityNew = new PersonDoctor();
        BeanUtils.copyProperties(doctor.get(), entityNew);

        if (!dto.getPersonEmail().equals(entityNew.getPersonEmail())) {
            personService.validateEmailExists(dto.getPersonEmail());
            entityNew.setPersonEmail(dto.getPersonEmail());
        }

        entityNew.setPersonName(dto.getPersonName());
        entityNew.setPersonStatus(dto.getPersonStatus());
        entityNew.setPersonSex(dto.getPersonSex());
        entityNew.setPersonBirthday(dto.getPersonBirthday());
        entityNew.setPersonLastUpdate(OffsetDateTime.now());

        repository.save(entityNew);
        return ResponseEntity.ok().build();
    }


    /**Inactive a doctor by Person ID.*/
    @Transactional
    public void delete(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isPresent()) {
            doctor.get().setPersonStatus(false);
            doctor.get().setPersonLastUpdate(OffsetDateTime.now());
            repository.save(doctor.get());
        } else {
            throw new NotFoundException("Doctor not found.");
        }
    }


    /**Renew professional register of doctor.*/
    @Transactional
    public void renewValidity(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new NotFoundException("Doctor not found.");

        int dayVal = doctor.get().getProfessionalRegisterValidity().getDayOfMonth();
        int monVal = doctor.get().getProfessionalRegisterValidity().getMonthValue();
        int yearVal = doctor.get().getProfessionalRegisterValidity().getYear();
        int dayNow = LocalDate.now().getDayOfMonth();
        int monNow = LocalDate.now().getMonthValue();
        int yearNow = LocalDate.now().getYear();

        LocalDate holderDate = LocalDate.of(yearVal, monVal, dayVal);
        if ((dayVal + (monVal - 1) + yearVal) == (dayNow + monNow + yearNow)) {
            yearVal = yearVal + 1;
            holderDate = LocalDate.of(yearVal, monVal, dayVal);
        }
        doctor.get().setProfessionalRegisterValidity(holderDate);

        repository.save(doctor.get());
    }


    /**Convert in doctor professional information list.*/
    public List<PersonDoctorInformationDto> convertToInfo() {
        List<PersonDoctor> doctorList = repository.findAll();
        List<PersonDoctorInformationDto> doctorFinalList = new ArrayList<>();
        doctorList.forEach(personDoctor -> doctorFinalList.add(modelMapper.map(personDoctor, PersonDoctorInformationDto.class)));
        return doctorFinalList;
    }

}
