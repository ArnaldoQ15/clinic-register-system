package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.DoctorRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;



    public Doctor searchByPersonId(Long personId) {
        return doctorRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Doctor not found."));
    }



    @Transactional
    public Doctor saveDoctor(Doctor doctor) {
        doctor.setPersonStatus(true);

        Boolean cpfInUse = doctorRepository.findByPersonDocumentCpf(doctor.getPersonDocumentCpf())
                .stream()
                .anyMatch(cpfDoctorExist -> !cpfDoctorExist.equals(doctor));

        Boolean emailInUse = doctorRepository.findByPersonEmail(doctor.getPersonEmail())
                .stream()
                .anyMatch(emailDoctorExist -> !emailDoctorExist.equals(doctor));

        if (emailInUse) {
            throw new BusinessException("There is already a doctor registered with this e-mail.");
        } else if (cpfInUse) {
            throw new BusinessException("There is already a doctor registered with this CPF.");
        }
        return doctorRepository.save(doctor);
    }



    @Transactional
    public Doctor updateDoctor(Doctor doctor) {
        doctor.setPersonStatus(true);
        OffsetDateTime registerDateDoc = doctor.getPersonLastRegisterDate();

        if (registerDateDoc == null) {
            registerDateDoc = OffsetDateTime.now();
        } else {
            registerDateDoc = doctor.getPersonLastRegisterDate();
        }
        doctor.setPersonLastRegisterDate(registerDateDoc);
        return doctorRepository.save(doctor);
    }



//    Inactive/active (if was inactive) a doctor by Person ID
    @Transactional
    public void changeStatusDoctor(Long personId) {
        Doctor doctor = this.searchByPersonId(personId);
        doctor.setPersonStatus(!doctor.getPersonStatus());
        doctorRepository.save(doctor);
    }



//    Renew professional register of doctor
    @Transactional
    public void renewValidity(Long personId) {
        Doctor doctor = this.searchByPersonId(personId);

        int dayVal = doctor.getProfessionalRegisterValidity().getDayOfMonth();
        int monVal = doctor.getProfessionalRegisterValidity().getMonthValue();
        int yearVal = doctor.getProfessionalRegisterValidity().getYear();
        int dayNow = LocalDate.now().getDayOfMonth();
        int monNow = LocalDate.now().getMonthValue();
        int yearNow = LocalDate.now().getYear();

        LocalDate holderDate = LocalDate.of(yearVal, monVal, dayVal);
        if ((dayVal + (monVal - 1) + yearVal) == (dayNow + monNow + yearNow)) {
            yearVal = yearVal + 1;
            holderDate = LocalDate.of(yearVal, monVal, dayVal);
        }
        doctor.setProfessionalRegisterValidity(holderDate);
        doctorRepository.save(doctor);
    }

}
