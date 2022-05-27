package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.DoctorHoursRepository;
import com.br.clinicregister.model.PersonDoctorAgenda;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SearchHoursAvailableService {

//    String disponibleHour[] =

    private DoctorHoursRepository doctorHoursRepository;

    private PersonDoctorAgenda personDoctorAgenda;

//    public void searchHoursAvailable(Long doctorId) {
//        Boolean checkHourBusy = doctorHoursRepository.findAll()
//                .stream()
//                .anyMatch(personDoctor -> Boolean.parseBoolean(personDoctorAgenda.getHoursList()));
//        }

}
