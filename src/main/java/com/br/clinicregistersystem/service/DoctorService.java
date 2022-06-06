package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.dto.DoctorDto;
import com.br.clinicregistersystem.dto.DoctorInformationDto;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.PersonDoctor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService {

    private PersonDoctorRepository personDoctorRepository;
    private ModelMapper modelMapper;


    /**Search a doctor by Person ID.*/
    public PersonDoctor searchByPersonId(Long personId) {
        return personDoctorRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Doctor not found."));
    }


    /**Save doctor on database.*/
    @Transactional
    public DoctorDto saveDoctor(PersonDoctor personDoctor) {
        personDoctor.setPersonStatus(true);
        addDoctorAge(personDoctor);
        personDoctorRepository.save(personDoctor);
        return convertOnceToDto(personDoctor);
    }


    public DoctorDto convertOnceToDto(PersonDoctor personDoctor) {
        DoctorDto doctorRequest = modelMapper.map(personDoctor, DoctorDto.class);
        return doctorRequest;
    }


    /**Set doctor's age on database.*/
    public void addDoctorAge (PersonDoctor personDoctor) {
        Period periodAge = Period.between(personDoctor.getPersonBirthday(), LocalDate.now());
        Integer realDoctorAge = Math.abs(periodAge.getYears());
        personDoctor.setPersonAge(realDoctorAge);
    }


    /**Validate if a doctor exists on database.*/
    public void validatePersonExists(PersonDoctor personDoctor) {
        Optional<PersonDoctor> personCpf = personDoctorRepository.findByPersonDocumentCpf(personDoctor.getPersonDocumentCpf());
        if (personCpf.isPresent())
            throw new BusinessException("There is already a doctor registered with this CPF.");

        Optional<PersonDoctor> personEmail = personDoctorRepository.findByPersonEmail(personDoctor.getPersonEmail());
        if (personEmail.isPresent())
            throw new BusinessException("There is already a doctor registered with this e-mail.");
    }


    /**Update the doctor on database.*/
    @Transactional
    public PersonDoctor updateDoctor(PersonDoctor personDoctor) {
        personDoctor.setPersonStatus(true);
        OffsetDateTime registerDateDoc = personDoctor.getPersonLastRegisterDate();

        if (registerDateDoc == null) {
            registerDateDoc = OffsetDateTime.now();
        } else {
            registerDateDoc = personDoctor.getPersonLastRegisterDate();
        }
        personDoctor.setPersonLastRegisterDate(registerDateDoc);
        return personDoctorRepository.save(personDoctor);
    }


    /**Inactive/active (if was inactive) a doctor by Person ID.*/
    @Transactional
    public void changeStatusDoctor(Long personId) {
        PersonDoctor personDoctor = this.searchByPersonId(personId);
        personDoctor.setPersonStatus(!personDoctor.getPersonStatus());
        personDoctorRepository.save(personDoctor);
    }


    /**Renew professional register of doctor.*/
    @Transactional
    public void renewValidity(Long personId) {
        PersonDoctor personDoctor = this.searchByPersonId(personId);

        int dayVal = personDoctor.getProfessionalRegisterValidity().getDayOfMonth();
        int monVal = personDoctor.getProfessionalRegisterValidity().getMonthValue();
        int yearVal = personDoctor.getProfessionalRegisterValidity().getYear();
        int dayNow = LocalDate.now().getDayOfMonth();
        int monNow = LocalDate.now().getMonthValue();
        int yearNow = LocalDate.now().getYear();

        LocalDate holderDate = LocalDate.of(yearVal, monVal, dayVal);
        if ((dayVal + (monVal - 1) + yearVal) == (dayNow + monNow + yearNow)) {
            yearVal = yearVal + 1;
            holderDate = LocalDate.of(yearVal, monVal, dayVal);
        }
        personDoctor.setProfessionalRegisterValidity(holderDate);
        personDoctorRepository.save(personDoctor);
    }


    /**Convert list Doctor in list DoctorDto.*/
    public List<DoctorDto> convertListToDto(List<PersonDoctor> personDoctor) {
        return personDoctor.stream().map(DoctorDto::new).collect(Collectors.toList());
    }


    /**Convert list Doctor professional information in list DoctorDto list.*/
    public List<DoctorInformationDto> convertToDto(List<PersonDoctor> personDoctor) {
        return personDoctor.stream().map(DoctorInformationDto::new).collect(Collectors.toList());
    }

}
