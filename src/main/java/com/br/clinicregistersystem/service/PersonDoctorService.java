package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.*;
import com.br.clinicregistersystem.exception.BusinessException;
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
        doctorOutDto.getPersonPhones().forEach(personPhone -> phoneOutDtos.add(modelMapper.map(personPhone,
                PersonPhoneOutDto.class)));

        doctorOutDto.setPersonAddresses(addressOutDtos);
        doctorOutDto.setPersonPhones(phoneOutDtos);

        return doctorOutDto;
    }


    /**Save doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctorOutDto> persist(PersonDoctorInDto dto) {
        validateCpfExists(dto);
        validateEmailExists(dto);

        PersonDoctor entityNew = new PersonDoctor();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        insertDoctorAge(entityNew);

        List<PersonAddress> personAddresses = new ArrayList<>();
        List<PersonPhone> personPhones = new ArrayList<>();

        dto.getPersonAddresses().forEach(personAddress -> personAddresses.add(modelMapper.map(personAddress, PersonAddress.class)));
        dto.getPersonPhones().forEach(personPhone -> personPhones.add(modelMapper.map(personPhone, PersonPhone.class)));

        entityNew.setPersonAddresses(personAddresses);
        entityNew.setPersonPhones(personPhones);

        entityNew.setPersonStatus(true);

        repository.save(entityNew);
        personAgendaService.createDoctorAgenda(dto);
        return ResponseEntity.ok().build();
    }


    /**Set doctor's age on database.*/
    public void insertDoctorAge(PersonDoctor doctor) {
        Period periodAge = Period.between(doctor.getPersonBirthday(), LocalDate.now());
        Integer realDoctorAge = Math.abs(periodAge.getYears());
        doctor.setPersonAge(realDoctorAge);
    }


    /**Validate if a doctor cpf exists on database.*/
    public void validateCpfExists(PersonDoctorInDto dto) {
        Optional<PersonDoctor> personCpf = repository.findByPersonDocumentCpf(dto.getPersonDocumentCpf());
        if (personCpf.isPresent())
            throw new BusinessException("There is already a doctor registered with this CPF.");
    }


    /**Validate if a doctor e-mail exists on database.*/
    private void validateEmailExists(PersonDoctorInDto dto) {
        Optional<PersonDoctor> personEmail = repository.findByPersonEmail(dto.getPersonEmail());
        if (personEmail.isPresent() && !personEmail.get().getPersonDocumentCpf().equals(dto.getPersonDocumentCpf()))
            throw new BusinessException("There is already a doctor registered with this e-mail.");
    }


    /**Update the doctor on database.*/
    @Transactional
    public ResponseEntity<PersonDoctorOutDto> update(Long personId, PersonDoctorInDto dto) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isEmpty())
            throw new BusinessException("Doctor not found.");

        if (Boolean.TRUE.equals(repository.existsByPersonEmail(dto.getPersonEmail())))
            validateEmailExists(dto);

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


    /**Inactive/active a doctor by Person ID.*/
    @Transactional
    public void delete(Long personId) {
        Optional<PersonDoctor> doctor = repository.findById(personId);
        if (doctor.isPresent()) {
            doctor.get().setPersonStatus(false);
            repository.save(doctor.get());

        } else {
            throw new BusinessException("Doctor not found.");
        }

    }


    /**Renew professional register of doctor.*/
    @Transactional
    public void renewValidity(Long personId) {
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
    }


    /**Convert in doctor professional information list.*/
    public List<PersonDoctorInformationDto> convertToInfo() {
        List<PersonDoctor> doctorList = repository.findAll();
        List<PersonDoctorInformationDto> doctorFinalList = new ArrayList<>();

        doctorList.forEach(personDoctor -> doctorFinalList.add(modelMapper.map(personDoctor, PersonDoctorInformationDto.class)));
        return doctorFinalList;
    }

}
