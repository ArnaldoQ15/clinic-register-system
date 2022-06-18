package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.*;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonAddress;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPhone;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDoctorService {

    @Autowired
    private PersonDoctorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonAgendaService personAgendaService;

    @Autowired
    private PersonService personService;


    /**Find all doctors on Database.*/
    public List<PersonDoctorOutDto> findAll() {
        List<PersonDoctor> doctorList = repository.findAll();
        List<PersonDoctorOutDto> doctorFinalList = new ArrayList<>();

        doctorList.forEach(personDoctor -> {
            List<PersonAddressOutDto> addressOutDtos = new ArrayList<>();
            List<PersonPhoneOutDto> phoneOutDtos = new ArrayList<>();
            PersonDoctorOutDto doctorOutDto = modelMapper.map(personDoctor, PersonDoctorOutDto.class);
            personDoctor.getPersonAddresses().forEach(personAddress -> addressOutDtos.add(modelMapper.map(personAddress,
                    PersonAddressOutDto.class)));
            personDoctor.getPersonPhones().forEach(personPhone -> phoneOutDtos.add(modelMapper.map(personPhone,
                    PersonPhoneOutDto.class)));

            doctorFinalList.add(doctorOutDto);
        });

        return doctorFinalList;
    }


    /**Search a doctor by Person ID.*/
    public PersonDoctorOutDto findId(Long personId) {
        Optional<PersonDoctor> doctorOptional = repository.findById(personId);
        if (doctorOptional.isEmpty())
            throw new BusinessException("Doctor not found.");

        PersonDoctor doctor = doctorOptional.get();
        List<PersonAddressOutDto> addressOutDtos = new ArrayList<>();
        List<PersonPhoneOutDto> phoneOutDtos = new ArrayList<>();

        PersonDoctorOutDto doctorOutDto = modelMapper.map(doctor, PersonDoctorOutDto.class);

        doctorOutDto.getPersonAddresses().forEach(personAddress -> addressOutDtos.add(modelMapper.map(personAddress,
                PersonAddressOutDto.class)));
        doctorOutDto.setPersonAddresses(addressOutDtos);

        doctorOutDto.getPersonPhones().forEach(personPhone -> phoneOutDtos.add(modelMapper.map(personPhone,
                PersonPhoneOutDto.class)));
        doctorOutDto.setPersonPhones(phoneOutDtos);

        return doctorOutDto;
    }


    /**Save doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctorOutDto> persist(PersonDoctorInDto dto) {
        personService.validateCpfExists(dto.getPersonDocumentCpf());
        personService.validateEmailExists(dto.getPersonEmail());

        PersonDoctor entityNew = new PersonDoctor();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        entityNew.setPersonAge(personService.insertAge(entityNew.getPersonBirthday()));

        List<PersonAddress> personAddresses = new ArrayList<>();
        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        personAddresses.forEach(personAddress -> personAddress.setPerson(modelMapper.map(entityNew, Person.class)));
        entityNew.setPersonAddresses(personAddresses);

        List<PersonPhone> personPhones = new ArrayList<>();
        dto.getPersonPhones().forEach(personPhone -> {
            PersonPhone phone = new PersonPhone();
            BeanUtils.copyProperties(personPhone, phone);
            phone.setPerson(modelMapper.map(entityNew, Person.class));
            personPhones.add(phone);
        });

        entityNew.setPersonPhones(personPhones);
        entityNew.setPersonStatus(true);

        repository.save(entityNew);
        personAgendaService.createDoctorAgenda(dto);
        return ResponseEntity.ok().build();
    }


    /**Update the doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctorOutDto> update(Long personId, PersonDoctorInDto dto) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new BusinessException("Doctor not found.");

        if (Boolean.TRUE.equals(repository.existsByPersonEmail(dto.getPersonEmail())))
            personService.validateEmailExists(dto.getPersonEmail());

        PersonDoctor entityNew = new PersonDoctor();
        BeanUtils.copyProperties(doctor.get(), entityNew);

        List<PersonAddress> personAddresses = new ArrayList<>();
        List<PersonPhone> personPhones = new ArrayList<>();

        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        dto.getPersonPhones().forEach(personPhone -> personPhones.add(modelMapper.map(personPhone, PersonPhone.class)));

        entityNew.setPersonStatus(dto.getPersonStatus());
        entityNew.setPersonAddresses(personAddresses);
        entityNew.setPersonPhones(personPhones);
        entityNew.setPersonBirthday(dto.getPersonBirthday());
        entityNew.setPersonPhones(personPhones);
        entityNew.setPersonLastUpdate(OffsetDateTime.now());

        repository.save(entityNew);
        return ResponseEntity.ok().build();
    }


    /**Inactive a doctor by Person ID.*/
    @Transactional
    public ResponseEntity<Void> delete(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isPresent()) {
            doctor.get().setPersonStatus(false);
            repository.save(doctor.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new BusinessException("Doctor not found.");
        }
    }


    /**Renew professional register of doctor.*/
    @Transactional
    public ResponseEntity<Void> renewValidity(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new BusinessException("Doctor not found.");

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
        return ResponseEntity.ok().build();
    }


    /**Convert in doctor professional information list.*/
    public List<PersonDoctorInformationDto> convertToInfo() {
        List<PersonDoctor> doctorList = repository.findAll();
        List<PersonDoctorInformationDto> doctorFinalList = new ArrayList<>();
        doctorList.forEach(personDoctor -> doctorFinalList.add(modelMapper.map(personDoctor, PersonDoctorInformationDto.class)));
        return doctorFinalList;
    }

}
