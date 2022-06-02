package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.DoctorHourRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.model.Consult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultService {

    private ConsultRepository consultRepository;
    private DoctorHourRepository doctorHourRepository;
    private DoctorHourService doctorHourService;


//    Find a consult by person ID
    public Consult searchByPersonId(Long personId) {
        return consultRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Consult not found."));
    }

////    Save a consult on database
//    @Transactional
//    public Consult saveConsult (Consult consult) {
//        consult.setRegisterDate(OffsetDateTime.now());
//        consult.setStatus(ConsultStatus.PENDING);
//
//        Boolean consultTester = doctorHourRepository.findBy(doctorHourService.)
//
//    }


}
